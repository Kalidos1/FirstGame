package game.level;

import game.Entity.Entity;
import game.graphics.Screen;
import game.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public abstract class Level {


    protected int width,height;
    protected int[] tilesInt;
    protected int[] tiles;
    protected int tile_size;

    public List<Entity> entities = new ArrayList<Entity>();
    public List<Entity> mobs = new ArrayList<Entity>();

    public static Level spawn = new SpawnLevel("res/textures/SpawnLevel.png");
    public static Level kampfEinfach = new KampfLevelEinfach("res/textures/Level.png");
    public static Level kampfMittel = new KampfLevelMittel("res/textures/Level.png");
    public static Level kampfSchwer= new KampfLevelSchwer("res/textures/Level.png");

    public Level(int width, int height) {
        this.height = height;
        this.width = width;
        tilesInt = new int [width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    protected void loadLevel(String path) {
    }



    protected void generateLevel() {
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 64; x++) {
                getTile(x,y);
            }
        }
        tile_size = 16;
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).update();
        }
    }

    private void time() {
    }

    public void addNPC(Entity e) {
        entities.add(e);
    }
    public void addMob(Entity e) {
        mobs.add(e);
    }


    //Method render, xScroll,yScroll = Player Coordinates
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll,yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1;x++) {
                getTile(x,y).render(x,y,screen);
            }
        }
        for (Entity entity : entities) {
            entity.render(screen);
        }
        for (Entity mob : mobs) {
            mob.render(screen);
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass;
        if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower;
        if (tiles[x + y * width] == 0xFF7F7F00) return Tile.rock;
        if (tiles[x + y * width] == 0xFF0065FF) return Tile.fenceVer1L;
        if (tiles[x + y * width] == 0xFFFF000C) return Tile.fenceHor1;
        if (tiles[x + y * width] == 0xFF23F7FF) return Tile.fenceVer2L;
        if (tiles[x + y * width] == 0xFFFF7700) return Tile.fenceHor2;
        if (tiles[x + y * width] == 0xFF2E00FF) return Tile.fenceVer1R;
        if (tiles[x + y * width] == 0xFF7AffE2) return Tile.fenceVer2R;
        if (tiles[x + y * width] == 0xFF6E007C) return Tile.water;
        if (tiles[x + y * width] == 0xFFB6AAFF) return Tile.road;
        return Tile.voidTile;
    }

    public abstract String toString();


}
