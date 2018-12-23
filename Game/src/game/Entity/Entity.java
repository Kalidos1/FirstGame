package game.Entity;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.Level;
import java.util.Random;

public class Entity {

    public int x , y;
    private boolean removed = false;
    public int schwierigkeit;
    protected Level level;
    public Sprite sprite;
    protected final Random random = new Random();

    public Entity() {

    }

    public Entity(int x,int y,Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public  void update() {

    }

    public void render(Screen screen) {
        if (sprite != null) screen.renderSprite(x,y,sprite,true);
    }

    public void remove() {
        //Remove from Level
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
    public void init(Level level) {
        this.level = level;
    }

    public int getSchwierigkeit() {
        return schwierigkeit;
    }

}
