package com.ld41.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Spider {

    int x;
    int y;
    int dir;
    int left = 1;
    int right = -1;
    Texture texture;
    Texture upTex[];
    Texture downTex[];
    Texture leftTex[];
    Texture rightTex[];
    DemoCharacter player;
    Rectangle r;
    float delta;
    float delta2;
    int frame;
    int health;
    boolean canDie;
    boolean deleteMe;
    boolean died;

    public Spider(int x, int y, DemoCharacter player) {

        this.x = x;
        this.y = y;
        this.dir = left;

        r = new Rectangle(this.x, this.y, 32, 32);

        upTex = new Texture[2];
        downTex = new Texture[2];
        leftTex = new Texture[2];
        rightTex = new Texture[2];
        this.player = player;
        upTex[0] = new Texture("map/spiderUp1.png");
        upTex[1] = new Texture("map/spiderUp2.png");
        downTex[0] = new Texture("map/spiderDown1.png");
        downTex[1] = new Texture("map/spiderDown2.png");
        leftTex[0] = new Texture("map/spiderLeft1.png");
        leftTex[1] = new Texture("map/spiderLeft2.png");
        rightTex[0] = new Texture("map/spiderRight1.png");
        rightTex[1] = new Texture("map/spiderRight2.png");

        health = 3;
        canDie = true;
        deleteMe = false;
        died = false;
        frame = 0;

        switch (dir) {

            case 0:
                texture = upTex[frame];
                break;

            case 1:
                texture = downTex[frame];
                break;

            case 2:
                texture = leftTex[frame];
                break;

            case 3:
                texture = rightTex[frame];
                break;

        }

        delta = 0;

    }

    public void render(SpriteBatch batch, Vector3 mPos) {

        delta += Gdx.graphics.getDeltaTime();

        r.x = x;
        r.y = y;

        batch.draw(this.texture, this.x, this.y);

        if (!canDie) {

            delta2 += Gdx.graphics.getDeltaTime();

        }

        if (delta2 >= 0.35f) {

            canDie = true;
            delta2 = 0;

        }

        if (delta >= 0.4f) {

            if (frame == 0) {

                frame = 1;

            } else {

                frame = 0;

            }

            switch (dir) {


                case -1:
                    texture = leftTex[frame];
                    break;

                case 1:
                    texture = rightTex[frame];
                    break;

            }

            delta = 0;

        }

        if (this.r.contains(player.r) || this.r.overlaps(player.r)) {

            died = true;

        }

        if (this.r.contains(mPos.x, mPos.y)) {

            if (Gdx.input.justTouched()) {

                Vector3 playerPos = new Vector3(player.getX(), player.getY(), 0);
                Vector3 pos = new Vector3(this.x, this.y, 0);
                int distFromPlayer = (int) pos.dst(playerPos);

                if (distFromPlayer <= 40) {

                    if (canDie) {

                        this.health--;
                        canDie = false;

                    }

                }

            }

        }

        if (health < 1) {

            deleteMe = true;

        }

    }

}
