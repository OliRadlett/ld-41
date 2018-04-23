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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Menu extends Screen_ {

    SpriteBatch batch;
    Button_ exitButton;
    OrthographicCamera camera;
    Vector3 mPos;
    Button_ toClickerMenuButton;
    Texture[] bg;
    List<Cloud> clouds;
    Random r;

    int frame;
    float anim;

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();
        r = new Random();

        // button to exit game
        exitButton = new Button_((Gdx.graphics.getWidth() / 2) - 135, 64, "exit");
        exitButton.onClick(this::Exit);
        // button to switch to main menu
        toClickerMenuButton = new Button_((Gdx.graphics.getWidth() / 2 - 135), 275, "toClickerMainMenu" );
        toClickerMenuButton.onClick(this::switchScreenToMainMenu);

        clouds = new ArrayList<Cloud>();

        bg = new Texture[4];

        for (int i = 0; i < 4; i++) {

            bg[i] = new Texture("castle/menu" + i + ".png");

        }

        for (int i = 1; i < 4; i++) {

           clouds.add(new Cloud(1280 / i, r.nextInt(125) + 650));

        }

        frame = 0;
        anim = 0;

    }

    @Override
    public void render(float delta) {

        anim += delta;

        if (anim >= 0.7f) {

            if (frame == 3) {

                frame = 0;

            } else {

                frame ++;

            }

            anim = 0;

        }

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        batch.draw(bg[frame], 0, 0);

        for (Cloud cloud : clouds) {

            cloud.render(batch);

        }

        exitButton.render(batch);
        toClickerMenuButton.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        exitButton.logic(mPos);
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

    public void Exit() {

        System.out.println("Bye bye!");
        Gdx.app.exit();
        this.dispose();

    }

    public void switchScreenToMainMenu() {
        getGame().setScreen(new ClickerMainMenu(getGame()));
        this.dispose();
    }

}
