package game.Entity.Mob;

import game.graphics.Screen;
import game.graphics.Sprite;

public class NPC3 extends Mob {

    public NPC3(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.NPC_3;
    }

    public void update() {
    }

    public void render(Screen screen) {
        screen.renderMob(x - 16,y - 16,sprite);
    }

    public String toString() {
        return "NPC3";
    }
}
