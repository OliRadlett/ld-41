package com.ld41.map;

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
    Texture textureLeft;
    Texture textureRight;
    Texture textureUp;
    Texture textureDown;
    Texture texture;
    Rectangle r;
    MapGeneration gen;

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

        textureLeft = new Texture("map/demoCharacter1.png");
        textureRight = new Texture("map/demoCharacter2.png");
        textureUp = new Texture("map/demoCharacter3.png");
        textureDown = new Texture("map/demoCharacter4.png");
        texture = textureRight;

    }

    public void render(SpriteBatch batch) {

        switch (dir) {

            case 0:
                texture = textureLeft;
                break;
            case 1:
                texture = textureRight;
                break;
            case 2:
                texture = textureUp;
                break;
            case 3:
                texture = textureDown;
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
