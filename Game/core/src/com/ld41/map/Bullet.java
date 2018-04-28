package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    float x;
    float y;
    int speed;
    float dX;
    float dY;
    Texture texture;

    public Bullet(float x, float y, float mX, float mY) {

        this.x = x;
        this.y = y;

        this.dX = mX - this.x;
        this.dY = mY - this.y;
        float dL = (float) Math.sqrt((this.dX * this.dX) + (this.dY * this.dY));
        this.dX = this.dX / dL;
        this.dY = this.dY / dL;

        this.speed = 200;

        this.texture = new Texture("map/bullet.png");

    }

    public void render(SpriteBatch batch) {

        this.x = this.x + (this.dX * this.speed * Gdx.graphics.getDeltaTime());
        this.y = this.y + (this.dY * this.speed * Gdx.graphics.getDeltaTime());

        batch.draw(texture, x, y);

    }

}
