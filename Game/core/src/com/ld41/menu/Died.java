package com.ld41.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;
import com.ld41.menu.clicker.ClickerMainMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class Died extends Screen_ {

    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 mPos;
    // Copy-pasted button and names, but is back button
    Button_ continueButton;
    Texture bg;

    public Died(Game game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        // button to switch to main menu
        continueButton = new Button_((Gdx.graphics.getWidth() / 2 - 150 ), Gdx.graphics.getHeight() - 400, "continueGame");
        continueButton.onClick(this::switchScreenToMainMenu);

        bg = new Texture("castle/died.png");

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        batch.draw(bg, 0, 0);

        continueButton.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        continueButton.logic(mPos);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void switchScreenToMainMenu() {

        getGame().setScreen(new ClickerMainMenu(getGame()));
        this.dispose();
    }

}
