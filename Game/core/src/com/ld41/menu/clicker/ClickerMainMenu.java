package com.ld41.menu.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;

public class ClickerMainMenu extends Screen_ {

    private Texture castle;
    OrthographicCamera camera;
    SpriteBatch batch;
    Button_ clickerButton;
    Vector3 mPos;
    int x;
    int goldCounter;

    public ClickerMainMenu(Game game) {
        super(game);

        }

    @Override
    public void show() {
        goldCounter = 0;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        // add main castle texture
        castle = new Texture(Gdx.files.internal("castle/starterCastle.png"));

        // add button that adds 1 gold to total
        clickerButton = new Button_((Gdx.graphics.getWidth() / 2 - 48), Gdx.graphics.getHeight() - 40, "clickGold");
        clickerButton.onClick(() -> increaseGold());
    }

    @Override
    public void render(float delta) {

        x = (Gdx.graphics.getWidth() / 2) - (castle.getWidth() / 2);

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        // draw castle texture and render clicker button
        batch.begin();
        batch.draw(castle, x, 0);
        clickerButton.render(batch);
        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        // add hover functionality to clicker button
        clickerButton.logic(mPos);

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

    public void increaseGold() {
        goldCounter ++;
        System.out.println(String.valueOf(goldCounter));
    }
}
