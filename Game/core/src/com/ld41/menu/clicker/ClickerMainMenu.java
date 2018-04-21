package com.ld41.menu.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    String goldString;
    BitmapFont font;
    Button_ dungeonButton;

    public ClickerMainMenu(Game game) {
        super(game);
    }


    @Override
    public void show() {
        goldCounter = 0;
        goldString = "Gold: 0";
        font = new BitmapFont();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        // add main castle texture
        castle = new Texture(Gdx.files.internal("castle/starterCastle.png"));

        // add button that adds 1 gold to total
        clickerButton = new Button_((Gdx.graphics.getWidth() / 2 - 48), Gdx.graphics.getHeight() - 40, "clickGold");
        clickerButton.onClick(() -> increaseGold());

        // add button that goes to dungeon... or will do when I make it do that
        //dungeonButton = new Button_(Gdx.graphics.getWidth() - 110, 10, "toDungeon");
        //dungeonButton.onClick(() ->  sendToDungeon());
        }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        // draw castle texture and buttons
        x = (Gdx.graphics.getWidth() / 2) - (castle.getWidth() / 2);
        batch.draw(castle, x, 0);
        clickerButton.render(batch);
        //dungeonButton.render(batch);

        // update gold counter
        font.draw(batch, goldString, 10, Gdx.graphics.getHeight() - 10);
        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        // add hover functionality to buttons
        clickerButton.logic(mPos);
        //dungeonButton.logic(mPos);

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

        goldString = "Gold: " + String.valueOf(goldCounter);
        System.out.println(String.valueOf(goldCounter));
    }

    public void sendToDungeon() {
        // TODO: I dunno, however Oli wants to do this bit
        System.out.println("PLACEHOLDER");
    }
}
