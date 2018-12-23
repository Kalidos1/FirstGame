package game.graphics;

import game.level.tile.Tile;

import java.util.Random;

//Screen Class for the Screen
public class Screen {


    public int width,height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1; // Arrays begin with 0!!!

    public int xOffset,yOffset;

    //Tiles(Kacheln) of the Map
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];

        //Loop to fill every Tile with a Random Color
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    //Clear Method to renew the Pixels so it doesnt overlap
    //If u don't clear the colors stay and u cant change them anymore
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    /*Method to render your screen
    public void renderscreen(int xOffset, int yOffset) {
        //2 For Loops to fill the screen, u fill one line after the other growing to the bottom
        for (int y = 0; y < height; y++) {
            int yp = y + yOffset;
            if (yp < 0 || yp >= height) continue;
            for (int x = 0; x < width; x++) {
                int xp = x + xOffset;
                if (xp < 0 || xp >= width) continue;
                pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];

                // Map Looping  (xx / 16) or (yy /16) ... same thing
                // & MAP_SIZE_MASK => Looping, if u want to access the 64th Pixel u begin at 0 (0-63 !Array!)
                // int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
                // pixels[x + y * width] = tiles[tileIndex];   //Fill the Pixels with the including Map Tile
            }
        }
    }
    */
    public void renderSprite(int xp,int yp,Sprite sprite,boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y+ yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa  < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; // Only Renders Tiles that u can see on Screen
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderMob(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < 32; y++) {
            int ya = y + yp;
            //int ys = 31 - y;
            for (int x = 0; x < 32; x++) {
                int xa = x + xp;
                //int xs = 31 - x; // Start at 31,30,etc. to 0 instead of 0 to 31 so it renders the sprite in reverse
                if (xa  < -32 || xa >= width || ya < 0 || ya >= height) break; // Only Renders Tiles that u can see on Screen
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x + y * 32];
                if (col != 0x78C380 && col != 0xFFFFFF && col != 0x000000 && col != 0x818B8B) pixels[xa + ya * width] = col;
            }
        }
    }
    //Method for Offset, if the player is moving the Coordinates change is depending on the player
    public void setOffset(int xOffset,int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
