package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;


public class Tile {

    public int x,y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile fenceHor1 = new FenceHorTile1(Sprite.fenceHor1);
    public static Tile fenceVer1L = new FenceVerTile1L(Sprite.fenceVer1L);
    public static Tile fenceHor2 = new FenceHorTile2(Sprite.fenceHor2);
    public static Tile fenceVer2L = new FenceVerTile2L(Sprite.fenceVer2L);
    public static Tile fenceVer1R = new FenceVerTile1R(Sprite.fenceVer1R);
    public static Tile fenceVer2R = new FenceVerTile2R(Sprite.fenceVer2R);
    public static Tile water = new WaterTile(Sprite.water);
    public static Tile road = new RoadTile(Sprite.road);


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public boolean solid() {
        return false;
    }


}
