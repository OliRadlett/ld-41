package com.ld41.menu.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ld41.core.Screen_;
import com.ld41.main.Game;

public class ClickerMainMenu extends Screen_ {

    private Texture castle;
    OrthographicCamera camera;
    SpriteBatch batch;

    public ClickerMainMenu(Game game) {
        super(game);

        }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        castle = new Texture(Gdx.files.internal("castle/mainCastle.png"));

    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(castle, 328, 0);
        batch.end();

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

    public static void Exit() {

        System.out.println("Bye bye!");
        System.exit(0);

    }

}
