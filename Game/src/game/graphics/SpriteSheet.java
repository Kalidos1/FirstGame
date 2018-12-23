package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//SpriteSheet Class to Load 2D-Images, (3D would be Model)
public class SpriteSheet {

    private String path; //Path were the SpriteSheet is
    public final int SIZE;
    public final int WIDTH,HEIGHT;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("res/textures/spritesheet.png",256);

    private Sprite[] sprites;

    public SpriteSheet(SpriteSheet sheet,int x,int y,int width,int height,int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        if (width == height) SIZE = width;
        else SIZE = -1;
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        for (int y0 = 0; y0 < h ; y0++) {
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }
        int frame = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {
                int[] spritepixels = new int [spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++) {
                    for (int x0 = 0; x0 < spriteSize; x0++) {
                        spritepixels[x0 + y0 * spriteSize] =
                                pixels[((x0 + xa * spriteSize) + (y0 + ya * spriteSize)) * WIDTH];
                    }
                }
                Sprite sprite = new Sprite(spritepixels,spriteSize,spriteSize);
                sprites[frame++] = sprite;
            }
        }
    }


    public SpriteSheet(String path,int size) {
        this.path = path;
        SIZE = size;
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1;
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[width * height];
        loadDifferentSizes(width,height);
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    private void load() {
        try {
            //We want the single Pixels and not the Image as whole
            BufferedImage image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,pixels,0,w); //The image that we load is stored in pixels
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDifferentSizes(int width, int height) {
        try {
            //We want the single Pixels and not the Image as whole
            BufferedImage image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();
            for (int y = 0; y < h;y++) {
                for (int x = 0; x < w;x++) {
                    image.getRGB(x,y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
