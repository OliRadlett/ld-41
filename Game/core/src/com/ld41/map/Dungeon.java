package com.ld41.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;
import com.ld41.menu.Died;
import com.ld41.menu.Treasure;

import java.util.ArrayList;
import java.util.List;

public class Dungeon extends Screen_{

    SpriteBatch batch, GUIbatch;
    Button_ generateButton;
    OrthographicCamera camera;
    Vector3 mPos;
    MapGeneration generator;
    int[][] map;
    Texture wall, wall_bottom, wall_top, floor1, floor2, chest, debug, debug2, almostlighting;
    Pixmap crosshair;
    List<Bullet> bullets;
    boolean canshoot;
    float canshootTimer;

    boolean wPressed, aPressed, dPressed, sPressed;

    public Dungeon(Game game) {

        super(game);

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        GUIbatch = new SpriteBatch();
        generateButton = new Button_(64, 64, "generate");
        generateButton.onClick(this::generate);
        wall = new Texture("map/wall.png");
        wall_bottom = new Texture("map/wall_bottom.png");
        wall_top = new Texture("map/wall_top.png");
        floor1 = new Texture("map/floor1.png");
        floor2 = new Texture("map/floor2.png");
        chest = new Texture("map/chest.png");
        almostlighting = new Texture("map/almostlighting.png");
        generator = new MapGeneration();
        map = new int[100][100];
        generate();
        camera.position.x = generator.character.getX() - 16;
        camera.position.y = generator.character.getY() - 16;
        camera.zoom = 0.5f;
        // Debug zoom:
        //camera.zoom = 4f;
        debug = new Texture("map/debug.png");
        debug2 = new Texture("map/debug2.png");
        crosshair = new Pixmap(Gdx.files.internal("map/crosshair.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(crosshair, 15, 15));
        bullets = new ArrayList<>();
        canshoot = true;
        canshootTimer = 0;

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

        });

    }

    @Override
    public void render(float delta) {

        canshootTimer += delta;

        camera.position.x = (generator.character.getX()) - 16;
        camera.position.y = (generator.character.getY()) - 16;

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        if (!canshoot) {

            if (canshootTimer > 0.2) {

                canshootTimer = 0;
                canshoot = true;

            }

        }

        if (Gdx.input.isTouched()) {

            if (canshoot) {

                bullets.add(new Bullet(generator.character.x, generator.character.y, mPos.x, mPos.y));
                canshoot = false;

            }

        }

        batch.begin();

        for (int i = 0; i < 75; i++) {

            for (int j = 0; j < 75; j++) {

                switch (map[i][j]) {

                    case 0:

                        batch.draw(floor1, i * floor1.getWidth(), j * floor1.getHeight());

                        break;

                    case 1:

                        batch.draw(wall, i * wall.getWidth(), j * wall.getHeight());
                        break;

                    case 2:

                        batch.draw(floor1, i * floor1.getWidth(), j * floor1.getHeight());
                        batch.draw(chest, i * chest.getWidth(), j * chest.getHeight());

                    case 3:
                        batch.draw(wall_bottom, i * wall_bottom.getWidth(), j * wall_bottom.getHeight());
                        break;

                    case 4:
                        batch.draw(wall_top, i * wall_top.getWidth(), j * wall_top.getHeight());
                        break;

                }

            }

        }

        generator.character.render(batch);

        if (generator.character.r.contains(generator.chestRect) || generator.chestRect.contains(generator.character.r) || generator.character.r.overlaps(generator.chestRect) || generator.chestRect.overlaps(generator.character.r)) {

            this.getGame().setScreen(new Treasure(this.getGame()));

        }

        for (Spider spider : generator.spiders) {

            spider.render(batch, mPos);

            for (Rectangle r : generator.collisions) {

                Rectangle xR = new Rectangle(spider.x - 1, spider.y, 34, 32);

                if (r.contains(xR) || xR.contains(r) || r.overlaps(xR) || xR.overlaps(r)) {

                    spider.dir *= -1;
                    break;

                }

            }

            spider.x += spider.dir;

            if (spider.deleteMe) {

                generator.spiders.remove(spider);
                break;

            }

            if (spider.died) {

                getGame().setScreen(new Died(getGame()));

            }

        }

        if (wPressed) {

            generator.character.dir = 2;

            boolean canMove = true;

            for (Rectangle r : generator.collisions) {

                Rectangle pR = new Rectangle(generator.character.getX(), generator.character.getY() + 2, 32, 32);

                if (r.contains(pR) || pR.contains(r) || r.overlaps(pR) || pR.overlaps(r)) {

                    canMove = false;
                    break;

                }

            }

            if (canMove) {

                generator.character.setY(generator.character.getY() + 2);

            }

        }

        if (aPressed) {

            generator.character.dir = 0;

            boolean canMove = true;

            for (Rectangle r : generator.collisions) {

                Rectangle pR = new Rectangle(generator.character.getX() - 2, generator.character.getY(), 32, 32);

                if (r.contains(pR) || pR.contains(r) || r.overlaps(pR) || pR.overlaps(r)) {

                    canMove = false;
                    break;

                }

            }

            if (canMove) {

                generator.character.setX(generator.character.getX() - 2);

            }

        }

        if (sPressed) {

            generator.character.dir = 3;

            boolean canMove = true;

            for (Rectangle r : generator.collisions) {

                Rectangle pR = new Rectangle(generator.character.getX(), generator.character.getY() - 2, 32, 32);

                if (r.contains(pR) || pR.contains(r) || r.overlaps(pR) || pR.overlaps(r)) {

                    canMove = false;
                    break;

                }

            }

            if (canMove) {

                generator.character.setY(generator.character.getY() - 2);

            }

        }


        if (dPressed) {

            generator.character.dir = 1;

            boolean canMove = true;

            for (Rectangle r : generator.collisions) {

                Rectangle pR = new Rectangle(generator.character.getX() + 2, generator.character.getY(), 32, 32);

                if (r.contains(pR) || pR.contains(r) || r.overlaps(pR) || pR.overlaps(r)) {

                    canMove = false;
                    break;

                }

            }

            if (canMove) {

                generator.character.setX(generator.character.getX() + 2);

            }

        }

        for (Bullet b : bullets) {

            b.render(batch);

        }

        batch.end();

        GUIbatch.begin();
        GUIbatch.draw(almostlighting, 0, 0);
        GUIbatch.end();
        // Not right but idk


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
        map = generator.GenerateMap(75, 75, 10);

    }

}
