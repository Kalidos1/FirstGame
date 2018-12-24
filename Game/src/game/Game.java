package game;

import game.Entity.Entity;
import game.Entity.Mob.*;
import game.Reihen.*;
import game.graphics.Screen;
import game.input.Keyboard;
import game.input.Mouse;
import game.level.Level;
import game.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    //Size of the Window
    public static int width = 600;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public static String title = "PogGame";

    //Thread is a sub process of .exe, it runs within the main process, so u can assign different Threads to one Process
    private Thread gamethread;
    private JFrame frame;
    private Keyboard key;
    private boolean running = false;
    private Screen screen;
    private Player player;
    private Level level;
    private Mouse mouse = new Mouse();
    private String s;
    private String options[] = {"Spielen","Schliessen"};
    private String optionsSCH[] = {"Einfach","Mittel","Schwer","Ich möchte etwas anderes üben"};
    private String optionsREIHE[] = {"1","2","3","4","5","6","7","8","9","10"};
    final ImageIcon iconGreen = new ImageIcon("res/textures/GreenCheckmark.png");
    final ImageIcon iconRed = new ImageIcon("res/textures/RedCross.png");
    final ImageIcon feuerwerk = new ImageIcon("res/textures/Feuerwerk.png");
    boolean geoeffnet = false;
    Random randomAufgabe = new Random();

    //BufferImage
    private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    //This access the data of the DataBuffer and the Raster from this Buffer
    //Need this to change the Picture/Image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        //Windowsize of the Game
        int n = JOptionPane.showOptionDialog(frame,"Aarons Spiel","Spiel",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
        if (n == 0) {
            s = JOptionPane.showInputDialog(frame,"Gebe deinen Namen ein:");
            if (s == null) {
                JOptionPane.showMessageDialog(frame,"Falsche Eingabe!");
                frame.setVisible(false);
                frame.dispose();
            }
        } else {
            frame.setVisible(false);
            frame.dispose();
        }
        Dimension size = new Dimension(width * scale,height * scale);
        setPreferredSize(size);

        screen = new Screen(width,height);
        frame = new JFrame();
        //Key Listener to move the Map around
        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate player_Spawn = new TileCoordinate(16,16);
        player = new Player(player_Spawn.getX(),player_Spawn.getY(),key); //Change x/y to change spawn location
        player.init(level);

        frame.addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    //Creates the game.exe and runs it with the Thread
    //Synchronized is to stabilize and for not crashing the processes
    public synchronized void start() {
        running = true;
        //"this" refers to Runnable, and runs the Run() method automatically
        gamethread = new Thread(this,"Display");
        gamethread.start();
    }


    //Stops the .exe with ExceptionCatching
    public synchronized void stop() {
        running = false;
        try {
            gamethread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    //Method for Running the .exe
    public void run() {
        //Calculates how often the Method runs the Updated() Method also calculates the FPS
        // The 60 Seconds are important, because u want to restrict the update time so it runs better
        //The FPS can run to infinity
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1_000_000_000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        while (running) {
            frame.requestFocus();
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }



    public void update() {
        //Move around the Map with the Coordinates
        key.update();
        player.update();
        level.update();
        interactionNPC();
        interactionMOB();
    }


    //Method to Render the Image
    public void render() {
        //Creates Buffer to improve the performance of the program
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); //U want always 3 Buffer, MAX improvement
            return;
        }

        screen.clear();
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        //This Loop transfers all the Pixels from the Screen to the ImageBuffer
        for (int i = 0; i < pixels.length;i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        //all Graphics are displayed here before g.dispose
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        g.setFont(new Font("Verdana",0,50));
        g.drawString("Name: " + s,75,75);
        g.drawString("Level: " + level.toString(),75,150);
        g.drawString("E/Mouse: Reden",75,850);
        g.drawString("F/Mouse: Kämpfen",75,925);
        g.dispose();
        bs.show();
    }




    public boolean interactionNPC() {
        for (Entity entity : level.entities) {
            int entxklein = entity.x - 30;
            int entxgross = entity.x + 30;
            int entyklein = entity.y - 30;
            int entygross = entity.y + 30;
            if (player.x <= entxgross && player.x >= entxklein) {
                if (player.y <= entygross && player.y >= entyklein) {
                        if ((key.interactNPC || Mouse.getMouseB() == 1)) {
                            if (!geoeffnet) {
                                geoeffnet = true;
                                int n = JOptionPane.showOptionDialog(frame,
                                        "Wähle eine Schwierigkeit!",
                                        "Schwierigkeit", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.QUESTION_MESSAGE, null,
                                        optionsSCH, optionsSCH[0]);
                                wählreihe(n,entity);
                            }

                        }
                        return true;
                }
            }
        }
        return false;
    }

    private int temp;

    public String randomAufgabe() {
        String[] help = new String[]{reihe.Aufgabe1(),reihe.Aufgabe2(),reihe.Aufgabe3(),reihe.Aufgabe4(),
                reihe.Aufgabe5(),reihe.Aufgabe6(),reihe.Aufgabe7(),reihe.Aufgabe8(),reihe.Aufgabe9(),reihe.Aufgabe10()};
        int test = randomAufgabe.nextInt(10);
        temp = test + 1;
        return help[test];
    }

    public boolean interactionMOB() {
        for (Entity mob : level.mobs) {
            int entxklein = mob.x - 30;
            int entxgross = mob.x + 30;
            int entyklein = mob.y - 30;
            int entygross = mob.y + 30;
            if (player.x <= entxgross && player.x >= entxklein) {
                if (player.y <= entygross && player.y >= entyklein) {
                    if ((key.interactMOB || Mouse.getMouseB() == 1)) {
                        if (!geoeffnet) {
                            if (mob.schwierigkeit == 1) {
                                if (mobeinfach()) {
                                    level.mobs.remove(mob);
                                    geoeffnet = false;
                                }
                            }
                            if (mob.schwierigkeit == 2) {
                                if (mobmittel()) {
                                    level.mobs.remove(mob);
                                    geoeffnet = false;
                                }
                            }
                            if (mob.schwierigkeit == 3) {
                                if (mobschwer()) {
                                    level.mobs.remove(mob);
                                    geoeffnet = false;
                                }
                            }
                            geoeffnet = true;
                            if (level.mobs.size() == 0) {
                                geoeffnet = false;
                                JOptionPane.showMessageDialog(frame,"Level Geschafft!",null,JOptionPane.INFORMATION_MESSAGE,feuerwerk);
                                ladeSpawnLevel();
                            }
                        }
                    }
                    return true;
                }
            }
        }
        geoeffnet = false;
        return false;
    }



    public boolean mobeinfach() {
        int tmp = 1;
        while(tmp > 0) {
            String s = JOptionPane.showInputDialog(frame, randomAufgabe());
            if (checkAufgabe(s)) {
                tmp--;
                JOptionPane.showMessageDialog(frame,"Richtig! Noch " + tmp + " Aufgaben",null,JOptionPane.INFORMATION_MESSAGE,iconGreen);
            } else {
                JOptionPane.showMessageDialog(frame,"Falsch! Noch " + tmp + "Aufgaben",null,JOptionPane.INFORMATION_MESSAGE,iconRed);
            }
        }
        geoeffnet = false;
        return tmp == 0;
    }

    public boolean mobmittel() {
        int tmp = 2;
        while(tmp > 0) {
            String s = JOptionPane.showInputDialog(frame, randomAufgabe());
            if (checkAufgabe(s)) {
                tmp--;
                JOptionPane.showMessageDialog(frame,"Richtig! Noch " + tmp + " Aufgaben",null,JOptionPane.INFORMATION_MESSAGE,iconGreen);
            } else {
                JOptionPane.showMessageDialog(frame,"Falsch! Noch " + tmp + "Aufgaben",null,JOptionPane.INFORMATION_MESSAGE,iconRed);
            }
        }
        geoeffnet = false;
        return tmp == 0;
    }

    public boolean mobschwer() {
        int tmp = 3;
        while(tmp > 0) {
            String s = JOptionPane.showInputDialog(frame, randomAufgabe());
            if (checkAufgabe(s)) {
                tmp--;
                JOptionPane.showMessageDialog(frame,"Richtig! Noch " + tmp + " Aufgaben",null,JOptionPane.INFORMATION_MESSAGE,iconGreen);
            } else {
                JOptionPane.showMessageDialog(frame,"Falsch! Noch " + tmp + "Aufgaben",null,JOptionPane.INFORMATION_MESSAGE,iconRed);
            }
        }
        geoeffnet = false;
        return tmp == 0;
    }

    public boolean checkAufgabe(String s) {
        if (s == null) return false;
        s = s.replaceAll(",",".");
        if (reihe.rechenart == 0) {
            int rechnung = Integer.parseInt(s);
            if ((temp + reihe.reihe) == rechnung) {
                return true;
            }
        }
        if (reihe.rechenart == 1) {
            int rechnung = Integer.parseInt(s);
            if ((temp * reihe.reihe) == rechnung) {
                return true;
            }
        }
        if (reihe.rechenart == 2) {
            int rechnung = Integer.parseInt(s);
            if ((temp - reihe.reihe) == rechnung) {
                return true;
            }
        }
        if (reihe.rechenart == 3) {
            float rechnung = Float.parseFloat(s);
            if (((float) (temp * reihe.reihe) / (float) reihe.reihe) == rechnung) {
                return true;
            }
        }
        return false;
    }




    Random randomx = new Random();
    Random randomy = new Random();

    public Reihe reihe;

    public void wählreihe(int x,Entity entity) {
        if (x == 0) {
            ReihenDialogEin(entity);
            ladeLevelEinfach();
            geoeffnet = false;
        } else if (x == 1) {
            ReihenDialogMit(entity);
            ladeLevelMittel();
            geoeffnet = false;
        } else if (x == 2) {
            ReihenDialogSch(entity);
            ladeLevelSchwer();
            geoeffnet = false;
        } else {
            player.x = 16 << 4;
            player.y = 16 << 4;
            geoeffnet = false;
        }
    }

    public void ladeSpawnLevel() {
        level = Level.spawn;
        player.x = 16 << 4;
        player.y = 16 << 4;
        player.init(level);
    }


    public void ladeLevelEinfach() {
        level = Level.kampfEinfach;
        level.addMob(new Skeleton(1 + randomx.nextInt(14),1 + randomy.nextInt(14),1));
        level.addMob(new Goblin(1 + randomx.nextInt(14),1 + randomy.nextInt(14),1));
        level.addMob(new SkeletonKnight(1 + randomx.nextInt(14),1 + randomy.nextInt(14),1));
        player.x = 7 << 4;
        player.y = 8 << 4;
        player.init(level);
    }

    public void ladeLevelMittel() {
        level = Level.kampfMittel;
        level.addMob(new Skeleton(1 + randomx.nextInt(14),1 + randomy.nextInt(14),2));
        level.addMob(new Goblin(1 + randomx.nextInt(14),1 + randomy.nextInt(14),2));
        level.addMob(new SkeletonKnight(1 + randomx.nextInt(14),1 + randomy.nextInt(14),2));
        level.addMob(new GoblinKnight(1 + randomx.nextInt(14),1 + randomy.nextInt(14),2));
        player.x = 7 << 4;
        player.y = 8 << 4;
        player.init(level);
    }

    public void ladeLevelSchwer() {
        level = Level.kampfSchwer;
        level.addMob(new Skeleton(1 + randomx.nextInt(14),1 + randomy.nextInt(14),3));
        level.addMob(new Goblin(1 + randomx.nextInt(14),1 + randomy.nextInt(14),3));
        level.addMob(new SkeletonKnight(1 + randomx.nextInt(14),1 + randomy.nextInt(14),3));
        level.addMob(new GoblinKnight(1 + randomx.nextInt(14),1 + randomy.nextInt(14),3));
        level.addMob(new GoblinKnight(1 + randomx.nextInt(14),1 + randomy.nextInt(14),3));
        player.x = 7 << 4;
        player.y = 8 << 4;
        player.init(level);
    }

    public void ReihenDialogEin(Entity entity) {
        int help = -1;
        int n = JOptionPane.showOptionDialog(frame,
                "Wähle eine Reihe!",
                "Reihenwahl", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                optionsREIHE, optionsREIHE[0]);
        if (entity.toString().equals("NPC1")) help = 0;
        if (entity.toString().equals("NPC2")) help = 1;
        if (entity.toString().equals("NPC3")) help = 2;
        if (entity.toString().equals("NPC4")) help = 3;
        if (n == 0) {
            reihe = new EinerReihe(help);
        } else if (n == 1) {
            reihe = new ZweierReihe(help);
        } else if (n == 2) {
            reihe = new DreierReihe(help);
        } else if (n == 3) {
            reihe = new ViererReihe(help);
        } else if (n == 4) {
            reihe = new FuenferReihe(help);
        } else if (n == 5) {
            reihe = new SechserReihe(help);
        } else if (n == 6) {
            reihe = new SiebenerReihe(help);
        } else if (n == 7) {
            reihe = new AchterReihe(help);
        } else if (n == 8) {
            reihe = new NeunerReihe(help);
        } else if (n == 9) {
            reihe = new ZehnerReihe(help);
        } else {
            player.x = 16 << 4;
            player.y = 16 << 4;
            geoeffnet = false;
        }
    }

    public void ReihenDialogMit(Entity entity) {
        int help = -1;
        int n = JOptionPane.showOptionDialog(frame,
                "Wähle eine Reihe!",
                "Reihenwahl", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                optionsREIHE, optionsREIHE[0]);
        if (entity.toString().equals("NPC1")) help = 0;
        if (entity.toString().equals("NPC2")) help = 1;
        if (entity.toString().equals("NPC3")) help = 2;
        if (entity.toString().equals("NPC4")) help = 3;
        if (n == 0) {
            reihe = new EinerReihe(help);
        } else if (n == 1) {
            reihe = new ZweierReihe(help);
        } else if (n == 2) {
            reihe = new DreierReihe(help);
        } else if (n == 3) {
            reihe = new ViererReihe(help);
        } else if (n == 4) {
            reihe = new FuenferReihe(help);
        } else if (n == 5) {
            reihe = new SechserReihe(help);
        } else if (n == 6) {
            reihe = new SiebenerReihe(help);
        } else if (n == 7) {
            reihe = new AchterReihe(help);
        } else if (n == 8) {
            reihe = new NeunerReihe(help);
        } else if (n == 9) {
            reihe = new ZehnerReihe(help);
        } else {
            player.x = 16 << 4;
            player.y = 16 << 4;
            geoeffnet = false;
        }
    }

    public void ReihenDialogSch(Entity entity) {
        int help = -1;
        int n = JOptionPane.showOptionDialog(frame,
                "Wähle eine Reihe!",
                "Reihenwahl", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                optionsREIHE, optionsREIHE[0]);
        if (entity.toString().equals("NPC1")) help = 0;
        if (entity.toString().equals("NPC2")) help = 1;
        if (entity.toString().equals("NPC3")) help = 2;
        if (entity.toString().equals("NPC4")) help = 3;
        if (n == 0) {
            reihe = new EinerReihe(help);
        } else if (n == 1) {
            reihe = new ZweierReihe(help);
        } else if (n == 2) {
            reihe = new DreierReihe(help);
        } else if (n == 3) {
            reihe = new ViererReihe(help);
        } else if (n == 4) {
            reihe = new FuenferReihe(help);
        } else if (n == 5) {
            reihe = new SechserReihe(help);
        } else if (n == 6) {
            reihe = new SiebenerReihe(help);
        } else if (n == 7) {
            reihe = new AchterReihe(help);
        } else if (n == 8) {
            reihe = new NeunerReihe(help);
        } else if (n == 9) {
            reihe = new ZehnerReihe(help);
        } else {
            player.x = 16 << 4;
            player.y = 16 << 4;
            geoeffnet = false;
        }
    }


    public static void main(String[] args) {
        //JFrame functions to Edit the Window
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(game.title);
        game.frame.add(game);
        game.frame.pack(); //Packs the Window
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Important, so it closes completely
        game.frame.setLocationRelativeTo(null); //Appears at the Center of the screen
        game.frame.setVisible(true);
        game.start();
    }
}
