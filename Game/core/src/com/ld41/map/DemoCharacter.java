package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DemoCharacter {

    int x;
    int y;
    int dir;
    int left;
    int right;
    int up;
    int down;
    int frame;
    Texture texture;
    Texture upTex[];
    Texture downTex[];
    Texture leftTex[];
    Texture rightTex[];
    Rectangle r;
    MapGeneration gen;
    float delta;

    public DemoCharacter(int x, int y, MapGeneration gen) {

        this.x = x;
        this.y = y;
        this.gen = gen;
        left = 0;
        right = 1;
        up = 2;
        down = 3;
        dir = right;
        r = new Rectangle(this.x, this.y, 32, 32);

        upTex = new Texture[2];
        downTex = new Texture[2];
        leftTex = new Texture[2];
        rightTex = new Texture[2];
        upTex[0] = new Texture("map/demoCharacter3_1.png");
        upTex[1] = new Texture("map/demoCharacter3_2.png");
        downTex[0] = new Texture("map/demoCharacter4_1.png");
        downTex[1] = new Texture("map/demoCharacter4_2.png");
        leftTex[0] = new Texture("map/demoCharacter1_1.png");
        leftTex[1] = new Texture("map/demoCharacter1_2.png");
        rightTex[0] = new Texture("map/demoCharacter2_1.png");
        rightTex[1] = new Texture("map/demoCharacter2_2.png");
        frame = 0;
        delta = 0;
        texture = rightTex[frame];

    }

    public void render(SpriteBatch batch) {

        delta += Gdx.graphics.getDeltaTime();

        if (delta >= 0.4f) {

            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {

                if (frame == 0) {

                    frame = 1;

                } else {

                    frame = 0;

                }

                delta = 0;

            }

        }

        switch (dir) {

            case 0:
                texture = leftTex[frame];
                break;
            case 1:
                texture = rightTex[frame];
                break;
            case 2:
                texture = upTex[frame];
                break;
            case 3:
                texture = downTex[frame];
                break;

        }

        batch.draw(texture, x, y);
        r.x = this.x;
        r.y = this.y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean canMove() {

        Rectangle rightRect = new Rectangle(this.getX() + 2, this.getY(), 32, 32);

        for (Rectangle r : gen.collisions) {

            if (rightRect.contains(r)) {

                return false;

            }

        }

        return true;

    }

}
