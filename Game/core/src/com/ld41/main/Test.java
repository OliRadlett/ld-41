package com.ld41.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.menu.Menu;

import java.util.ArrayList;
import java.util.Random;

public class Test extends Screen_ {

    SpriteBatch batch;
    Button_ backButton;
    OrthographicCamera camera;
    Vector3 mPos;
    Texture entity;
    Texture[] blood;
    Random r;
    ArrayList<TestBloodSplatter> bloodList;

    public Test(Game game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();
        backButton = new Button_(64, Gdx.graphics.getHeight() - 128, "back");
        backButton.onClick(() -> Back());
        r = new Random();
        entity = new Texture("test/entity.png");

        bloodList = new ArrayList<TestBloodSplatter>();

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        batch.draw(entity, 400, 400);

        backButton.render(batch);

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

            bloodList.add(new TestBloodSplatter(64, Gdx.graphics.getHeight() / 2, 0, 50, 5));

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            bloodList.add(new TestBloodSplatter(64, Gdx.graphics.getHeight() / 2, 0, 10, 5));

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

            bloodList.add(new TestBloodSplatter(64, Gdx.graphics.getHeight() / 2, 0, 10, 5));

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

            bloodList.add(new TestBloodSplatter(64, Gdx.graphics.getHeight() / 2, 0, 10, 5));

        }

        for (TestBloodSplatter blood: bloodList) {

            blood.render(batch);

        }

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        backButton.logic(mPos);

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

    public void Back() {

        getGame().setScreen(new Menu(getGame()));

    }

}
