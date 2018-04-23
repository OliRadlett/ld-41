package com.ld41.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Cloud {

    int x;
    int y;
    int type;
    Random r;
    Texture[] texture;

    public Cloud(int x, int y) {

        this.x = x;
        this.y = y;
        r = new Random();
        texture = new Texture[5];

        for (int i = 0; i < 5; i++) {

            texture[i] = new Texture("castle/cloud" + i + ".png");

        }

        this.type = r.nextInt(4);

    }

    public void render(SpriteBatch batch) {

        batch.draw(texture[this.type], this.x, this.y);

        this.x += 1;

        if (this.x > 1400) {

            this.x = - 100;

        }

    }

}
