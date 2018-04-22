package com.ld41.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DemoCharacter {

    int x;
    int y;
    Texture texture;

    public DemoCharacter(int x, int y) {

        this.x = x;
        this.y = y;

        texture = new Texture("map/demoCharacter.png");

    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, x, y);

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

}
