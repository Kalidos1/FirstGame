package game.Entity.Mob;

import game.graphics.Screen;
import game.graphics.Sprite;

public class NPC2 extends Mob {

    public NPC2(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.NPC_2;
    }

    public void update() {
    }

    public void render(Screen screen) {
        screen.renderMob(x - 16,y - 16,sprite);
    }

    public String toString() {
        return "NPC2";
    }
}
