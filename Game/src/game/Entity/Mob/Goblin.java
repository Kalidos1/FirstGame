package game.Entity.Mob;


import game.graphics.Screen;
import game.graphics.Sprite;

public class Goblin extends Mob {


    public Goblin(int x, int y,int schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.MOB_Goblin;
    }

    public void update() {
    }

    public void render(Screen screen) {
        screen.renderMob(x - 16,y - 16,sprite);
    }

    public String toString() {
        return "Goblin";
    }



}