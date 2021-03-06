package game.level;

import game.Entity.Mob.Goblin;
import game.Entity.Mob.Skeleton;
import game.Entity.Mob.SkeletonKnight;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class KampfLevelEinfach extends Level {

    public KampfLevelEinfach(String path) {
        super(path);
    }


    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int w = height = image.getWidth();
            int h = width = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }
    }


    protected void generateLevel() {
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 64; x++) {
                getTile(x, y);
            }
        }
    }

    public String toString() {
        return "Kampf Level: Einfach";
    }
}