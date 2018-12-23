package game.level;



import game.Entity.Mob.NPC1;
import game.Entity.Mob.NPC2;
import game.Entity.Mob.NPC3;
import game.Entity.Mob.NPC4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int w = height = image.getWidth();
            int h = width = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0,0,w,h, tiles,0,w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }
        addNPC(new NPC1(4,5));
        addNPC(new NPC2(4,28));
        addNPC(new NPC3(28,5));
        addNPC(new NPC4(28,28));
    }


    protected void generateLevel() {
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 64; x++) {
                getTile(x,y);
            }
        }
    }


}
