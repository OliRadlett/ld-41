package com.ld41.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DemoCharacter {

    int x;
    int y;
    Texture texture;
    Rectangle r;
    MapGeneration gen;

    public DemoCharacter(int x, int y, MapGeneration gen) {

        this.x = x;
        this.y = y;
        this.gen = gen;
        r = new Rectangle(this.x, this.y, 32, 32);

        texture = new Texture("map/demoCharacter.png");

    }

    public void render(SpriteBatch batch) {

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
