package com.ld41.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class Button_ {

    int x;
    int y;
    int width;
    int height;
    Texture texture;
    Texture textureNoHover;
    Texture textureHover;
    Rectangle bounds;
    Runnable callback;

    public Button_(int x, int y, String name) {

        this.x = x;
        this.y = y;
        this.textureNoHover = new Texture("buttons/" + name + ".png");
        this.textureHover = new Texture("buttons/" + name + "Hover.png");
        this.texture = textureHover;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int screenX, int screenY, int pointer, int button) {

                callback.run();

                return false;

            }

        });

    }

    public void onClick(Runnable onClick) {

        this.callback = onClick;

    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, x, y);

    }

    public void logic(Vector3 mPos) {

        if (this.bounds.contains(mPos.x, mPos.y)) {

            this.texture = textureHover;

        } else {

            this.texture = textureNoHover;

        }

    }

}
