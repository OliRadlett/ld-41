package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    float delta;
    int frame;

    public Spider(int x, int y) {

        this.x = x;
        this.y = y;
        this.dir = left;

        upTex = new Texture[2];
        downTex = new Texture[2];
        leftTex = new Texture[2];
        rightTex = new Texture[2];
        upTex[0] = new Texture("map/spiderUp1.png");
        upTex[1] = new Texture("map/spiderUp2.png");
        downTex[0] = new Texture("map/spiderDown1.png");
        downTex[1] = new Texture("map/spiderDown2.png");
        leftTex[0] = new Texture("map/spiderLeft1.png");
        leftTex[1] = new Texture("map/spiderLeft2.png");
        rightTex[0] = new Texture("map/spiderRight1.png");
        rightTex[1] = new Texture("map/spiderRight2.png");

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

    public void render(SpriteBatch batch) {

        delta += Gdx.graphics.getDeltaTime();

        batch.draw(this.texture, this.x, this.y);

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

    }
}
