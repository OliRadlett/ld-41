package com.ld41.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;
import com.ld41.map.Dungeon;

public class Menu extends Screen_ {

    SpriteBatch batch;
    Button_ exitButton;
    Button_ mapTestButton;
    OrthographicCamera camera;
    Vector3 mPos;

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();
        exitButton = new Button_((Gdx.graphics.getWidth() / 2) - 48, 64, "exit");
        mapTestButton = new Button_((Gdx.graphics.getWidth() / 2) - 48, 256, "generate");
        exitButton.onClick(() -> Exit());
        mapTestButton.onClick(() -> testMap());

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        exitButton.render(batch);
        mapTestButton.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        exitButton.logic(mPos);
        mapTestButton.logic(mPos);

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

    public void Exit() {

        System.out.println("Bye bye!");
        Gdx.app.exit();
        this.dispose();

    }

    public void testMap() {

        getGame().setScreen(new Dungeon(getGame()));
        this.dispose();

    }

}
