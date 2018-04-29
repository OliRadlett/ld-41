package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

    float x;
    float y;
    int speed;
    float dX;
    float dY;
    Texture texture;
    Rectangle r;

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

        this.r = new Rectangle((int)this.x, (int)this.y, this.texture.getWidth(), this.texture.getHeight());

    }

    public void render(SpriteBatch batch) {

        this.x = this.x + (this.dX * this.speed * Gdx.graphics.getDeltaTime());
        this.y = this.y + (this.dY * this.speed * Gdx.graphics.getDeltaTime());
        this.r.x = (int) this.x;
        this.r.y = (int) this.y;

        // TODO Bullet cleanup and stop at walls
        // TODO Stop initial shot firing two bullets
        // TODO Stop bullet firing from "To Dungeon" button press

        batch.draw(texture, x, y);

    }

}
