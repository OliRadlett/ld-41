package com.ld41.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

public class Treasure extends Screen_ {

    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 mPos;
    Button_ continueButton;
    Texture bg;
    boolean ownsLeftTower;
    boolean ownsRightTower;
    boolean ownsLeftTurret;
    boolean ownsRightTurret;
    boolean ownsMainTower;
    boolean leftTowerBlueprint;
    boolean rightTowerBlueprint;
    boolean leftTurretBlueprint;
    boolean rightTurretBlueprint;
    boolean mainTowerBlueprint;
    int goldCounter;
    String goldString;
    int gps;
    String gpsString;
    int pickaxePrice;
    int pickaxeCounter;
    String pickaxeStringPrice;
    String pickaxeString;
    int minerCounter;
    int minerPrice;
    String minerStringPrice;
    String minerString;
    int ponyCounter;
    int ponyPrice;
    String ponyStringPrice;
    String ponyString;
    int dynamiteCounter;
    int dynamitePrice;
    String dynamiteStringPrice;
    String dynamiteString;
    BitmapFont font;
    String givenBlueprint;

    public Treasure(Game game) {
        super(game);
    }

    @Override
    public void show() {

        font = new BitmapFont();
        font.getData().setScale(2);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        batch = new SpriteBatch();

        // button to switch to main menu
        continueButton = new Button_((Gdx.graphics.getWidth() / 2 - 150 ), Gdx.graphics.getHeight() - 600, "continueGame");
        continueButton.onClick(this::switchScreenToMainMenu);

        bg = new Texture("castle/Background.png");

        ArrayList<String> blueprintArray = new ArrayList<String>();

        try {

            File file = new File("save.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            // restoring gold
            goldCounter = Integer.parseInt(properties.getProperty("gold"));
            gps = Integer.parseInt(properties.getProperty("gps"));
            goldString = "Gold: " + goldCounter;
            gpsString = "Gold per Second: " + gps;

            // restoring pickaxe upgrades
            pickaxePrice = Integer.parseInt(properties.getProperty("pickaxePrice"));
            pickaxeCounter = Integer.parseInt(properties.getProperty("pickaxeCounter"));
            pickaxeStringPrice = "Price: " + pickaxePrice;
            pickaxeString = "Pickaxes Upgrades: " + pickaxeCounter;

            // restoring miners
            minerCounter = Integer.parseInt(properties.getProperty("minerCounter"));
            minerPrice = Integer.parseInt(properties.getProperty("minerPrice"));
            minerStringPrice = "Price: " + minerPrice;
            minerString = "Miners: " + minerCounter;

            // restoring ponies
            ponyCounter = Integer.parseInt(properties.getProperty("ponyCounter"));
            ponyPrice = Integer.parseInt(properties.getProperty("ponyPrice"));
            ponyStringPrice = "Price: " + ponyPrice;
            ponyString = "Miners: " + ponyCounter;

            // restoring dynamite
            dynamiteCounter = Integer.parseInt(properties.getProperty("dynamiteCounter"));
            dynamitePrice = Integer.parseInt(properties.getProperty("dynamitePrice"));
            dynamiteStringPrice = "Price: " + dynamitePrice;
            dynamiteString = "Dynamite: " + dynamiteCounter;

            ownsRightTower = Boolean.parseBoolean(properties.getProperty("haveRightTower"));
            ownsLeftTower = Boolean.parseBoolean(properties.getProperty("haveLeftTower"));
            ownsRightTurret = Boolean.parseBoolean(properties.getProperty("haveRightTurret"));
            ownsLeftTurret = Boolean.parseBoolean(properties.getProperty("haveLeftTurret"));
            ownsMainTower = Boolean.parseBoolean(properties.getProperty("haveMainTower"));


            if (!ownsRightTower) {
                blueprintArray.add("rightTowerBlueprint");
            }
            if (!ownsLeftTower) {
                blueprintArray.add("leftTowerBlueprint");
            }
            if (!ownsRightTurret) {
                blueprintArray.add("rightTurretBlueprint");
            }
            if (!ownsLeftTurret) {
                blueprintArray.add("leftTurretBlueprint");
            }
            if (!ownsMainTower) {
                blueprintArray.add("mainTowerBlueprint");
            }

            System.out.println(blueprintArray);

            Random randomGenerator = new Random();

            if (blueprintArray != null) {
                int size = blueprintArray.size();
                System.out.println(String.valueOf(size));
                int num = randomGenerator.nextInt(size);
                System.out.println(String.valueOf(num));

                givenBlueprint = blueprintArray.get(num);

                if (givenBlueprint.equals("rightTowerBlueprint")) {
                    rightTowerBlueprint = true;
                }
                if (givenBlueprint.equals("leftTowerBlueprint")) {
                    leftTowerBlueprint = true;
                }
                if (givenBlueprint.equals("leftTurretBlueprint")) {
                    leftTurretBlueprint = true;
                }
                if (givenBlueprint.equals("rightTurretBlueprint")) {
                    rightTurretBlueprint = true;
                }
                if (givenBlueprint.equals("mainTowerBlueprint")) {
                    mainTowerBlueprint = true;
                }
            }


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

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();


        batch.begin();

        batch.draw(bg, 0, 0);
        continueButton.render(batch);

        if (givenBlueprint.equals("rightTowerBlueprint")) {
            font.draw(batch, "Congratulations, you found the Right Tower Blueprint!",
                    Gdx.graphics.getWidth() - 1000, Gdx.graphics.getHeight() / 2 + 200);
        }
        if (givenBlueprint.equals("leftTowerBlueprint")) {
            font.draw(batch, "Congratulations, you found the Left Tower Blueprint!",
                    Gdx.graphics.getWidth() - 1000, Gdx.graphics.getHeight() / 2 + 200);
        }
        if (givenBlueprint.equals("leftTurretBlueprint")) {
            font.draw(batch, "Congratulations, you found the Left Turret Blueprint!",
                    Gdx.graphics.getWidth() - 1000, Gdx.graphics.getHeight() / 2 + 200);
        }
        if (givenBlueprint.equals("rightTurretBlueprint")) {
            font.draw(batch, "Congratulations, you found the Right Turret Blueprint!",
                    Gdx.graphics.getWidth() - 1000, Gdx.graphics.getHeight() / 2 + 200);
        }
        if (givenBlueprint.equals("mainTowerBlueprint")) {
            font.draw(batch, "Congratulations, you found the Main Tower Blueprint!",
                    Gdx.graphics.getWidth() - 1000, Gdx.graphics.getHeight() / 2 + 200);
        }



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

        try {
            Properties properties = new Properties();

            properties.setProperty("gold", String.valueOf(goldCounter));
            properties.setProperty("gps", String.valueOf(gps));

            properties.setProperty("pickaxePrice", String.valueOf(pickaxePrice));
            properties.setProperty("pickaxeCounter", String.valueOf(pickaxeCounter));

            properties.setProperty("minerPrice", String.valueOf(minerPrice));
            properties.setProperty("minerCounter", String.valueOf(minerCounter));

            properties.setProperty("ponyPrice", String.valueOf(ponyPrice));
            properties.setProperty("ponyCounter", String.valueOf(ponyCounter));

            properties.setProperty("dynamitePrice", String.valueOf(dynamitePrice));
            properties.setProperty("dynamiteCounter", String.valueOf(dynamiteCounter));

            properties.setProperty("haveRightTower", String.valueOf(ownsRightTower));
            properties.setProperty("haveLeftTower", String.valueOf(ownsLeftTower));
            properties.setProperty("haveRightTurret", String.valueOf(ownsRightTurret));
            properties.setProperty("haveLeftTurret", String.valueOf(ownsLeftTurret));
            properties.setProperty("haveMainTower", String.valueOf(ownsMainTower));

            properties.setProperty("leftTowerBlueprint", String.valueOf(leftTowerBlueprint));
            properties.setProperty("rightTowerBlueprint", String.valueOf(rightTowerBlueprint));
            properties.setProperty("leftTurretBlueprint", String.valueOf(leftTurretBlueprint));
            properties.setProperty("rightTurretBlueprint", String.valueOf(rightTurretBlueprint));
            properties.setProperty("mainTowerBlueprint", String.valueOf(mainTowerBlueprint));

            File file = new File("save.properties");
            System.out.println(file.getAbsolutePath());
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut,"Ludum Dare 41 Save" );

        } catch (IOException e) {
            e.printStackTrace();
        }

        getGame().setScreen(new ClickerMainMenu(getGame()));
        this.dispose();
    }

}
