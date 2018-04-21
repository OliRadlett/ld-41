package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;

public class Dungeon extends Screen_{

    SpriteBatch batch;
    Button_ generateButton;
    OrthographicCamera camera;
    Vector3 mPos;
    MapGeneration generator;
    int[][] map;
    Texture wall;

    boolean wPressed, aPressed, dPressed, sPressed;

    public Dungeon(Game game) {

        super(game);

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();
        generateButton = new Button_(64, 64, "generate");
        generateButton.onClick(() -> generate());
        wall = new Texture("map/wall.png");
        generator = new MapGeneration();
        map = new int[100][100];
        generate();

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {

                if (keycode == Input.Keys.W) {

                    wPressed = true;

                }

                if (keycode == Input.Keys.S) {

                    sPressed = true;

                }

                if (keycode == Input.Keys.A) {

                    aPressed = true;

                }

                if (keycode == Input.Keys.D) {

                    dPressed = true;

                }

                return false;

            }

            @Override
            public boolean keyUp(int keycode) {

                if (keycode == Input.Keys.W) {

                    wPressed = false;

                }

                if (keycode == Input.Keys.S) {

                    sPressed = false;

                }

                if (keycode == Input.Keys.A) {

                    aPressed = false;

                }

                if (keycode == Input.Keys.D) {

                    dPressed = false;

                }

                return false;

            }

            @Override
            public boolean scrolled(int amount) {

                if (amount == 1) {

                    camera.zoom += 1 * 0.2f;

                }

                if (amount == -1) {

                    camera.zoom -= 1 * 0.2f;

                }

                return false;

            }
        });

    }

    @Override
    public void render(float delta) {

        if (wPressed) {

            camera.position.y += 8;

        }

        if (sPressed) {

            camera.position.y -= 8;

        }

        if (aPressed) {

            camera.position.x -= 8;

        }

        if (dPressed) {

            camera.position.x += 8;

        }

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 100; j++) {

                switch (map[i][j]) {

                    case 1:

                        batch.draw(wall, i * 16, j * 16);

                }

            }

        }

        generateButton.render(batch);

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);
        generateButton.logic(mPos);


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

    public void generate() {

        System.out.println("Generating new map");
        map = generator.GenerateMap(100, 100, 1);

    }

}
