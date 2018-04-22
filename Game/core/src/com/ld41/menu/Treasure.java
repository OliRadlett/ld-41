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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Treasure extends Screen_ {

    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 mPos;
    Button_ toClickerMenuButton;
    Texture bg;
    boolean ownsLeftTower;
    boolean ownsRightTower;
    boolean ownsLeftTurret;
    boolean ownsRightTurret;
    boolean ownsMainTower;

    public Treasure(Game game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        // button to switch to main menu
        toClickerMenuButton = new Button_((Gdx.graphics.getWidth() / 2 - 135), 275, "toClickerMainMenu");
        toClickerMenuButton.onClick(() -> switchScreenToMainMenu());

        bg = new Texture("castle/background.png");

        if (new File("save.properties").isFile()) {

            try {

                File file = new File("save.properties");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                fileInput.close();

                ownsRightTower = Boolean.parseBoolean(properties.getProperty("haveRightTower"));
                ownsLeftTower = Boolean.parseBoolean(properties.getProperty("haveLeftTower"));
                ownsRightTurret = Boolean.parseBoolean(properties.getProperty("haveRightTurret"));
                ownsLeftTurret = Boolean.parseBoolean(properties.getProperty("haveLeftTurret"));
                ownsMainTower = Boolean.parseBoolean(properties.getProperty("haveMainTower"));

            } catch (FileNotFoundException e) {

                e.printStackTrace();

                ownsLeftTower = false;
                ownsRightTower = false;
                ownsLeftTurret = false;
                ownsRightTurret = false;
                ownsMainTower = false;

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        //TODO choose random unowned blueprint and write it to file

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        batch.draw(bg, 0, 0);

        toClickerMenuButton.render(batch);

        //TODO render blueprint in some way
        //TODO make blueprint do something?, idk whats real anymore

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        toClickerMenuButton.logic(mPos);

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
