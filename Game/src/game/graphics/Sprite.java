package game.graphics;

public class Sprite {

    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private int width,height;
    protected SpriteSheet sheet;
    //Creation of the Sprite, use Sprite.grass to access it (size,where to find it x,where to find it y,sheet)

    //Level Sprites:
    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16,2,0,SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16,2,2, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16,2,4, SpriteSheet.tiles);
    public static Sprite fenceVer1L = new Sprite(16,5,2, SpriteSheet.tiles);
    public static Sprite fenceHor1 = new Sprite(16,5,0, SpriteSheet.tiles);
    public static Sprite fenceVer2L = new Sprite(16,5,3, SpriteSheet.tiles);
    public static Sprite fenceHor2 = new Sprite(16,6,0, SpriteSheet.tiles);
    public static Sprite fenceVer1R = new Sprite(16,7,2, SpriteSheet.tiles);
    public static Sprite fenceVer2R = new Sprite(16,7,3, SpriteSheet.tiles);
    public static Sprite water = new Sprite(16,4,5, SpriteSheet.tiles);
    public static Sprite road = new Sprite(16,2,6, SpriteSheet.tiles);

    //NPC Sprites:
    public static Sprite NPC_1 = new Sprite(32,0,4,SpriteSheet.tiles);
    public static Sprite NPC_2 = new Sprite(32,1,4,SpriteSheet.tiles);
    public static Sprite NPC_3 = new Sprite(32,2,4,SpriteSheet.tiles);
    public static Sprite NPC_4 = new Sprite(32,3,4,SpriteSheet.tiles);

    //Mobs Sprites:
    public static Sprite MOB_Goblin = new Sprite(32,3,3,SpriteSheet.tiles);
    public static Sprite MOB_Skeleton = new Sprite(32,4,3,SpriteSheet.tiles);
    public static Sprite MOB_GoblinKnight = new Sprite(32,4,4,SpriteSheet.tiles);
    public static Sprite MOB_SkeletonKnight = new Sprite(32,4,5,SpriteSheet.tiles);
    public static Sprite MOB_SkeletonMage = new Sprite(32,4,6,SpriteSheet.tiles);


    //Player Sprites:
    public static Sprite player_back = new Sprite(32,5,0,SpriteSheet.tiles);
    public static Sprite player_forward= new Sprite(32,5,3,SpriteSheet.tiles);
    public static Sprite player_left = new Sprite(32,5,1,SpriteSheet.tiles);
    public static Sprite player_right = new Sprite(32,5,2,SpriteSheet.tiles);

    public static Sprite player_forward_1 = new Sprite(32,6,3,SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(32,7,3,SpriteSheet.tiles);

    public static Sprite player_back_1 = new Sprite(32,6,0,SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32,7,0,SpriteSheet.tiles);

    public static Sprite player_left_1 = new Sprite(32,6,1,SpriteSheet.tiles);
    public static Sprite player_left_2 = new Sprite(32,7,1,SpriteSheet.tiles);

    public static Sprite player_right_1 = new Sprite(32,6,2,SpriteSheet.tiles);
    public static Sprite player_right_2 = new Sprite(32,7,2,SpriteSheet.tiles);

    protected Sprite(SpriteSheet sheet,int width,int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }

    //Class for Only 1 Sprite in the SpriteSheet
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size,int colour) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    public Sprite(int width,int height,int colour) {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < pixels.length;i++) {
            pixels[i] = colour;
        }
    }

    protected Sprite(int[] pixels,int width,int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }


    //Extracting a single Sprite out of a SpriteSheet
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                //The complete SpriteSheets gets accessed and only one Sheet gets extracted
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
