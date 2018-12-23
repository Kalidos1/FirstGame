package game.Entity.Mob;

import game.Entity.Entity;
import game.graphics.Screen;
import game.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    protected boolean walking = false;

    public void move(int xa,int ya) {

        if (xa > 0) dir = 1; //Direction EAST (+x)
        if (xa < 0) dir = 3; //Direction WEST (-x)
        if (ya > 0) dir = 2; //Direction SOUTH (+y)
        if (ya < 0) dir = 0; //Direction NORTH (-y)

        if (!collision(0,ya)) {
            y += ya;
        }
        if (!collision(xa,0)) {
            x += xa;
        }
    }

    public abstract void update();

    public abstract void render(Screen screen);

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 2 - 1) / 16;
            int yt = ((y + ya) + c / 2 * 12 + 2) / 16;
            if (level.getTile(xt,yt).solid()) solid = true;
        }
        return solid;
    }



}
