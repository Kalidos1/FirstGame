package game.Entity.Mob;

import game.graphics.Screen;
import game.graphics.Sprite;


import game.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;


    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public Player(int x, int y,Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public void update() {



        int xa = 0, ya = 0;
        if (anim < 10000) anim++;
        else anim = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;



        if (xa != 0 || ya != 0) {
            move(xa,ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void render(Screen screen) {
        if (dir == 0) {
            sprite = Sprite.player_forward;
            if (walking) {
                if (anim % 40 > 30) {
                    sprite = Sprite.player_forward_1;
                } else if (anim % 40 > 20) {
                    sprite = Sprite.player_forward;
                } else if (anim % 40 > 10) {
                    sprite = Sprite.player_forward_2;
                } else {
                    sprite = Sprite.player_forward;
                }
            }
        }
        if (dir == 1) {
            sprite = Sprite.player_right;
            if (walking) {
                if (anim % 40 > 30) {
                    sprite = Sprite.player_right_1;
                } else if (anim % 40 > 20) {
                    sprite = Sprite.player_right;
                } else if (anim % 40 > 10) {
                    sprite = Sprite.player_right_2;
                } else {
                    sprite = Sprite.player_right;
                }
            }
        }
        if (dir == 2) {
            sprite = Sprite.player_back;
            if (walking) {
                if (anim % 40 > 30) {
                    sprite = Sprite.player_back_1;
                } else if (anim % 40 > 20) {
                    sprite = Sprite.player_back;
                } else if (anim % 40 > 10) {
                    sprite = Sprite.player_back_2;
                } else {
                    sprite = Sprite.player_back;
                }
            }
        }
        if (dir == 3) {
            sprite = Sprite.player_left;
            if (walking) {
                if (anim % 40 > 30) {
                    sprite = Sprite.player_left_1;
                } else if (anim % 40 > 20) {
                    sprite = Sprite.player_left;
                } else if (anim % 40 > 10) {
                    sprite = Sprite.player_left_2;
                } else {
                    sprite = Sprite.player_left;
                }
            }
        }
        screen.renderMob(x - 16,y - 16, sprite);
    }

}
