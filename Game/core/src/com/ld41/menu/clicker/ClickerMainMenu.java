package com.ld41.menu.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ld41.core.Button_;
import com.ld41.core.Screen_;
import com.ld41.main.Game;
import com.ld41.map.Dungeon;
import com.ld41.menu.Menu;

import java.io.*;
import java.util.Properties;


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
    Button_ minerButton;
    int minerCounter;
    int pickaxeCounter;
    int pickaxePrice;
    String pickaxeString;
    String pickaxeStringPrice;
    String minerString;
    String minerStringPrice;
    float deltaTimer;
    int height;
    int width;
    int minerPrice;
    int gps;
    Button_ mainMenuButton;
    Button_ pickaxeButton;
    String gpsString;
    Button_ ponyButton;
    int ponyPrice;
    int ponyCounter;
    String ponyStringPrice;
    String ponyString;
    Boolean haveRightTower;
    Button_ rightTowerButton;
    Texture rightTowerBody;
    Boolean haveLeftTower;
    Button_ leftTowerButton;
    Texture leftTowerBody;
    boolean haveRightTurret;
    Button_ rightTurretButton;
    Texture rightTurret;

    public ClickerMainMenu(Game game) {
        super(game);
    }


    @Override
    public void show() {

        font = new BitmapFont();

        // checks to see if there's a save file
        if (new File("save.properties").isFile()) {

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

                // restoring towers
                haveRightTower = Boolean.parseBoolean(properties.getProperty("haveRightTower"));
                haveLeftTower = Boolean.parseBoolean(properties.getProperty("haveLeftTower"));
                haveRightTurret = Boolean.parseBoolean(properties.getProperty("haveRightTurret"));


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            goldCounter = 0;
            gps = 0;
            gpsString = "Gold per Second: 0";
            goldString = "Gold: 0";

            pickaxePrice = 25;
            pickaxeString = "Pickaxe Upgrades: 0";
            pickaxeStringPrice = "Price: " + pickaxePrice;

            minerString = "Miners: 0";
            minerPrice = 50;
            minerStringPrice = "Price: " + minerPrice;

            ponyPrice = 120;
            ponyString = "Ponies: 0";
            ponyStringPrice = "Price: " + ponyPrice;

            haveRightTower = false;
            haveLeftTower = false;
            haveRightTurret = false;
        }

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width, height);
        camera.position.x = width / 2;
        camera.position.y = height / 2;
        batch = new SpriteBatch();

        // add main castle texture
        castle = new Texture(Gdx.files.internal("castle/castleMain.png"));

        // add button that adds 1 gold to total
        clickerButton = new Button_((width / 2 - 48), height - 40, "clickGold");
        clickerButton.onClick(() -> increaseGold());

        // add button that goes to dungeon... todo: or will do when I make it do that
        dungeonButton = new Button_(width - 110, 10, "toDungeon");
        dungeonButton.onClick(() ->  sendToDungeon());

        // add button that sends the user back to main menu
        mainMenuButton = new Button_(10, 10, "toMainMenu");
        mainMenuButton.onClick(() -> sendToMainMenu());


        /*
            Gold upgrades menu
         */

        // add upgrade pickaxe button
        pickaxeButton = new Button_(10, Gdx.graphics.getHeight() - 100, "upgradePickaxe");
        pickaxeButton.onClick(() -> upgradePickaxe());

        // add miner button
        minerButton = new Button_(10, Gdx.graphics.getHeight() - 180, "addMiner");
        minerButton.onClick(() -> addMiner());

        // add pit pony button
        ponyButton = new Button_(10, Gdx.graphics.getHeight() - 260, "trainPony");
        ponyButton.onClick(() -> trainPony());

        //todo: add dynamite

        /*

            Castle upgrades Menu

         */

        rightTowerBody = new Texture(Gdx.files.internal("castle/rightTowerBody.png"));
        leftTowerBody = new Texture(Gdx.files.internal("castle/leftTowerBody.png"));
        rightTurret = new Texture(Gdx.files.internal("castle/rightTurret.png"));

        // add right tower upgrade button
        rightTowerButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 100, "buildRightTower");
        rightTowerButton.onClick(() -> buildRightTower());

        // add left tower upgrade button
        leftTowerButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 180, "buildLeftTower");
        leftTowerButton.onClick(() -> buildLeftTower());

        // add right tower
        rightTurretButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 260, "buildRightTurret");
        rightTurretButton.onClick(() -> buildRightTurret());
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
        dungeonButton.render(batch);
        minerButton.render(batch);
        mainMenuButton.render(batch);
        pickaxeButton.render(batch);
        ponyButton.render(batch);

        if (!haveRightTower) {
            rightTowerButton.render(batch);
        }
        if (!haveLeftTower) {
            leftTowerButton.render(batch);
        }
        if (!haveRightTurret) {
            rightTurretButton.render(batch);
        }

        // update gold counter and gps counter
        font.draw(batch, goldString, 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, gpsString, 115, Gdx.graphics.getHeight() - 10);

        // add and update pickaxe counter and price
        font.draw(batch, pickaxeStringPrice, 115, Gdx.graphics.getHeight() - 45);
        font.draw(batch, pickaxeString, 115, Gdx.graphics.getHeight() - 75);

        // add and update miner counter and price
        font.draw(batch, minerStringPrice, 115, Gdx.graphics.getHeight() - 125);
        font.draw(batch, minerString, 115, Gdx.graphics.getHeight() - 155);

        // add and update pony counter and price
        font.draw(batch, ponyStringPrice, 115, Gdx.graphics.getHeight() - 205);
        font.draw(batch, ponyString, 115, Gdx.graphics.getHeight() - 235);

        if (haveRightTower) {
            batch.draw(rightTowerBody, x , 0);
        }
        if (haveLeftTower) {
            batch.draw(leftTowerBody, x, 0);
        }
        if (haveRightTurret) {
            batch.draw(rightTurret, x, 0);
        }

        batch.end();

        mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        // add hover functionality to buttons
        clickerButton.logic(mPos);
        dungeonButton.logic(mPos);
        minerButton.logic(mPos);
        mainMenuButton.logic(mPos);
        pickaxeButton.logic(mPos);
        ponyButton.logic(mPos);

        if (!haveRightTower) {
            rightTowerButton.logic(mPos);
        }
        if (!haveLeftTower) {
            leftTowerButton.logic(mPos);
        }
        if (!haveRightTurret) {
            rightTurretButton.logic(mPos);
        }

        // updates the gold every second
        deltaTimer += delta;

        if (deltaTimer > 1) {
            updateGold();
            deltaTimer = 0;
        }
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

    public void upgradePickaxe() {
        if (goldCounter >= pickaxePrice) {
            pickaxeCounter ++;
            pickaxeString = "Pickaxe Upgrades: " + String.valueOf(pickaxeCounter);
            goldCounter -= pickaxePrice;
            goldString = "Gold: " + String.valueOf(goldCounter);

            if (pickaxeCounter > 0) {
                pickaxePrice += pickaxePrice * 0.6;
                pickaxeStringPrice = "Price " + pickaxePrice;
                gps += pickaxeCounter;
                gpsString = "Gold per Second: " + gps;
            }
        } else {
            System.out.println("Not enough gold");
        }
    }

    public void addMiner() {

        if (goldCounter >= minerPrice) {
            minerCounter ++;
            minerString = "Miners: " + String.valueOf(minerCounter);
            goldCounter = goldCounter - minerPrice;
            goldString = "Gold: " + String.valueOf(goldCounter);

            // increases price of a miner by a third each time a new one is bought
            if (minerCounter > 0) {
                minerPrice += minerPrice * 0.5;
                minerStringPrice = "Price: " + minerPrice;
                gps += minerCounter * 2;
                gpsString = "Gold per Second: " + gps;
            }

        } else {
            System.out.println("Not enough gold");
        }
    }

    public void trainPony() {

        if (goldCounter >= ponyPrice) {
            ponyCounter ++;
            ponyString = "Ponies: " + String.valueOf(ponyCounter);
            goldCounter -= ponyPrice;
            goldString = "Gold: " + String.valueOf(goldCounter);

            if (ponyCounter > 0) {
                ponyPrice += ponyPrice * 0.5;
                ponyStringPrice = "Price " + ponyPrice;
                gps += ponyCounter * 5;
                gpsString = "Gold per second: " + gps;
            }
        } else {
            System.out.println("Not enough gold");
        }
    }

    public void buildRightTower() {

        if (goldCounter >= 1) {
            haveRightTower = true;

        } else {
            System.out.println("Not enough gold");
        }
    }

    public void buildLeftTower() {
        if (goldCounter >= 1) {
            haveLeftTower = true;
        } else {
            System.out.println("Not enough gold");
        }
    }

    public void buildRightTurret() {
        if (goldCounter >= 1) {
            haveRightTurret = true;
        } else {
            System.out.println("Not enough gold");
        }
    }


    public void sendToDungeon() {
        saveToFile();

        getGame().setScreen(new Dungeon(getGame()));
        this.dispose();

    }

    public void sendToMainMenu() {

        saveToFile();

        getGame().setScreen(new Menu(getGame()));
        this.dispose();

    }

    public void updateGold() {

        goldCounter = goldCounter + gps;
        goldString = "Gold: " + String.valueOf(goldCounter);

    }

    public void saveToFile() {
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

            properties.setProperty("haveRightTower", String.valueOf(haveRightTower));
            properties.setProperty("haveLeftTower", String.valueOf(haveLeftTower));
            properties.setProperty("haveRightTurret", String.valueOf(haveRightTurret));

            File file = new File("save.properties");
            System.out.println(file.getAbsolutePath());
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut,"Ludum Dare 41 Save" );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
