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

    private Texture castle, bg;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Button_ clickerButton;
    private int goldCounter;
    private String goldString;
    private BitmapFont font;
    private Button_ dungeonButton;
    private Button_ minerButton;
    private int minerCounter;
    private int pickaxeCounter;
    private int pickaxePrice;
    private String pickaxeString;
    private String pickaxeStringPrice;
    private int dynamiteCounter;
    private int dynamitePrice;
    private String dynamiteString;
    private String dynamiteStringPrice;
    private String minerString;
    private String minerStringPrice;
    private float deltaTimer;
    private int minerPrice;
    private int gps;
    private Button_ mainMenuButton;
    private Button_ pickaxeButton;
    private String gpsString;
    private Button_ ponyButton;
    private int ponyPrice;
    private int ponyCounter;
    private String ponyStringPrice;
    private String ponyString;
    private boolean haveRightTower;
    private Button_ rightTowerButton;
    private Texture rightTowerBody;
    private boolean haveLeftTower;
    private Button_ leftTowerButton;
    private Texture leftTowerBody;
    private boolean haveRightTurret;
    private Button_ rightTurretButton;
    private Texture rightTurret;
    private boolean haveLeftTurret;
    private Button_ leftTurretButton;
    private Texture leftTurret;
    private boolean haveMainTower;
    private Button_ mainTowerButton;
    private Texture mainTower;
    private Button_ dynamiteButton;
    private boolean leftTowerBlueprint;
    private boolean rightTowerBlueprint;
    private boolean leftTurretBlueprint;
    private boolean rightTurretBlueprint;
    private boolean mainTowerBlueprint;
    private Button_ pickaxeTransparentButton;
    private Button_  minerTransparentButton;
    private Button_ ponyTransparentButton;
    private Button_ dynamiteTransparentButton;
    private Button_ rightTowerButtonTransparent;
    private Button_ leftTowerButtonTransparent;
    private Button_ rightTurretButtonTransparent;
    private Button_ leftTurretButtonTransparent;
    private Button_ mainTowerButtonTransparent;

    private static final int miner = 0;
    private static final int pony = 1;
    private static final int dynamite = 2;
    private static final int pickaxe = 3;
    private int[] prices;
    private int[] upgrades;
    private int[] upgradeEffects;
    private String[] strings;


    public ClickerMainMenu(Game game) {
        super(game);
    }


    @Override
    public void show() {

        font = new BitmapFont();

        prices = new int[4];
        upgrades = new int[4];
        upgradeEffects = new int[4];
        strings = new String[4];

        prices[pickaxe] = 25;
        prices[miner] = 50;
        prices[pony] = 120;
        prices[dynamite] = 250;

        upgradeEffects[miner] = 2;
        upgradeEffects[pony] = 5;
        upgradeEffects[dynamite] = 10;
        upgradeEffects[pickaxe] = 1;

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

                // restoring dynamite
                dynamiteCounter = Integer.parseInt(properties.getProperty("dynamiteCounter"));
                dynamitePrice = Integer.parseInt(properties.getProperty("dynamitePrice"));
                dynamiteStringPrice = "Price: " + dynamitePrice;
                dynamiteString = "Dynamite: " + dynamiteCounter;

                // restoring towers
                haveRightTower = Boolean.parseBoolean(properties.getProperty("haveRightTower"));
                haveLeftTower = Boolean.parseBoolean(properties.getProperty("haveLeftTower"));
                haveRightTurret = Boolean.parseBoolean(properties.getProperty("haveRightTurret"));
                haveLeftTurret = Boolean.parseBoolean(properties.getProperty("haveLeftTurret"));
                haveMainTower = Boolean.parseBoolean(properties.getProperty("haveMainTower"));

                // restoring blueprint states
                rightTowerBlueprint = Boolean.parseBoolean(properties.getProperty("rightTowerBlueprint"));
                leftTowerBlueprint = Boolean.parseBoolean(properties.getProperty("leftTowerBlueprint"));
                leftTurretBlueprint = Boolean.parseBoolean(properties.getProperty("leftTurretBlueprint"));
                rightTurretBlueprint = Boolean.parseBoolean(properties.getProperty("rightTurretBlueprint"));
                mainTowerBlueprint = Boolean.parseBoolean(properties.getProperty("mainTowerBlueprint"));

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

            dynamitePrice = 250;
            dynamiteString = "Dynamite: 0";
            dynamiteStringPrice = "Price: " + dynamitePrice;

            haveRightTower = false;
            haveLeftTower = false;
            haveRightTurret = false;
            haveLeftTurret = false;
            haveMainTower = false;
        }

        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width, height);
        camera.position.x = width / 2;
        camera.position.y = height / 2;
        batch = new SpriteBatch();

        // add main castle texture
        castle = new Texture(Gdx.files.internal("castle/castleMain.png"));

        // Add background texture
        bg = new Texture("castle/Background2.png");

        // add button that adds 1 gold to total
        clickerButton = new Button_((width / 2 - 48), Gdx.graphics.getHeight() - 80, "clickGold");
        clickerButton.onClick(this::increaseGold);

        // add button that goes to dungeon
        dungeonButton = new Button_(width - 110, 10, "toDungeon");
        dungeonButton.onClick(this::sendToDungeon);

        // add button that sends the user back to main menu
        mainMenuButton = new Button_(10, 10, "toMainMenu");
        mainMenuButton.onClick(this::sendToMainMenu);

        /*
            Gold upgrades menu
         */

        // add upgrade pickaxe button
        pickaxeButton = new Button_(10, Gdx.graphics.getHeight() - 100, "upgradePickaxe");
        pickaxeButton.onClick(() -> buyUpgrade(pickaxe));

        pickaxeTransparentButton = new Button_(10, Gdx.graphics.getHeight() - 100,
                "upgradePickaxeTransparent");
        pickaxeTransparentButton.onClick(() -> buyUpgrade(pickaxe));

        // add miner button
        minerButton = new Button_(10, Gdx.graphics.getHeight() - 180, "addMiner");
        minerButton.onClick(() -> buyUpgrade(miner));

        minerTransparentButton = new Button_(10, Gdx.graphics.getHeight() - 180, "addMinerTransparent");
        minerTransparentButton.onClick(() -> buyUpgrade(miner));

        // add pit pony button
        ponyButton = new Button_(10, Gdx.graphics.getHeight() - 260, "trainPony");
        ponyButton.onClick(() -> buyUpgrade(pony));

        ponyTransparentButton = new Button_(10, Gdx.graphics.getHeight() - 260, "trainPonyTransparent");
        ponyTransparentButton.onClick(() -> buyUpgrade(pony));

        //add buy dynamite button
        dynamiteButton = new Button_(10, Gdx.graphics.getHeight() - 340, "buyDynamite");
        dynamiteButton.onClick(() -> buyUpgrade(dynamite));

        dynamiteTransparentButton = new Button_(10, Gdx.graphics.getHeight() - 340,
                "buyDynamiteTransparent");
        dynamiteTransparentButton.onClick(() -> buyUpgrade(dynamite));

        /*

            Castle upgrades Menu

         */

        rightTowerBody = new Texture(Gdx.files.internal("castle/rightTowerBody.png"));
        leftTowerBody = new Texture(Gdx.files.internal("castle/leftTowerBody.png"));
        rightTurret = new Texture(Gdx.files.internal("castle/rightTurret.png"));
        leftTurret = new Texture(Gdx.files.internal("castle/leftTurret.png"));
        mainTower = new Texture(Gdx.files.internal("castle/mainTower.png"));

        // add right tower upgrade button
        rightTowerButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 100, "buildRightTower");
        rightTowerButton.onClick(this::buildRightTower);

        rightTowerButtonTransparent = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 100, "buildRightTowerTransparent");
        rightTowerButtonTransparent.onClick(this::buildRightTower);

        // add left tower upgrade button
        leftTowerButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 180, "buildLeftTower");
        leftTowerButton.onClick(this::buildLeftTower);

        leftTowerButtonTransparent = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 180, "buildLeftTowerTransparent");
        leftTowerButtonTransparent.onClick(this::buildLeftTurret);

        // add right turret
        rightTurretButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 260, "buildRightTurret");
        rightTurretButton.onClick(this::buildRightTurret);

        rightTurretButtonTransparent = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 260, "buildRightTurretTransparent");
        rightTurretButtonTransparent.onClick(this::buildRightTurret);

        // add left turret
        leftTurretButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 340, "buildLeftTurret");
        leftTurretButton.onClick(this::buildLeftTurret);

        leftTurretButtonTransparent = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 340, "buildLeftTurretTransparent");
        leftTurretButtonTransparent.onClick(this::buildLeftTurret);

        // add main tower
        mainTowerButton = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 420, "buildMainTower");
        mainTowerButton.onClick(this::buildMainTower);

        mainTowerButtonTransparent = new Button_(Gdx.graphics.getWidth() - 105, Gdx.graphics.getHeight() - 420, "buildMainTowerTransparent");
        mainTowerButtonTransparent.onClick(this::buildMainTower);
    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        batch.draw(bg, 0, 0);

        // draw castle texture and buttons
        int x = (Gdx.graphics.getWidth() / 2) - (castle.getWidth() / 2);
        batch.draw(castle, x, 0);

        clickerButton.render(batch);
        dungeonButton.render(batch);
        mainMenuButton.render(batch);

        if (goldCounter >= prices[miner]) {
            minerButton.render(batch);
        } else {
            minerTransparentButton.render(batch);
        }

        if (goldCounter >= prices[pickaxe]) {
            pickaxeButton.render(batch);
        } else {
            pickaxeTransparentButton.render(batch);
        }

        if (goldCounter >= prices[pony]) {
            ponyButton.render(batch);
        } else {
            ponyTransparentButton.render(batch);
        }

        if (goldCounter >= prices[dynamite]) {
            dynamiteButton.render(batch);
        } else {
            dynamiteTransparentButton.render(batch);
        }

        String rightTowerPrice = "Price: 1000\n        +\n  Blueprint";
        String leftTowerPrice = "Price: 2000\n        +\n  Blueprint";
        String rightTurretPrice = "Price: 3000\n        +\n  Blueprint";
        String leftTurretPrice = "Price: 4000\n        +\n  Blueprint";
        String mainTowerPrice = "Price: 5000\n        +\n  Blueprint";


        // render transparent buttons if user does not have money and blueprints. Render nothing if user
        // already has the upgrade. Render normal buttons if they can buy it

        if (!haveRightTower) {
            if (rightTowerBlueprint && goldCounter > 1000) {
                rightTowerButton.render(batch);
                font.draw(batch, rightTowerPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 45);
            } else {
                rightTowerButtonTransparent.render(batch);
                font.draw(batch, rightTowerPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 45);
            }
        }

        if (!haveLeftTower) {
            if (leftTowerBlueprint && goldCounter > 2000) {
               leftTowerButton.render(batch);
               font.draw(batch, leftTowerPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 125);
           } else {
               leftTowerButtonTransparent.render(batch);
               font.draw(batch, leftTowerPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 125);
           }
        }

        if (!haveRightTurret) {
            if (rightTurretBlueprint && goldCounter > 3000) {
                rightTurretButton.render(batch);
                font.draw(batch, rightTurretPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 205);
            } else {
                rightTurretButtonTransparent.render(batch);
                font.draw(batch, rightTurretPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 205);
            }
        }

        if (!haveLeftTurret) {
            if (leftTurretBlueprint && goldCounter > 4000) {
                leftTurretButton.render(batch);
                font.draw(batch, leftTurretPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 285);
            } else {
                leftTurretButtonTransparent.render(batch);
                font.draw(batch, leftTurretPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 285);
            }
        }

        if (!haveMainTower) {
            if (mainTowerBlueprint && goldCounter > 5000) {
                mainTowerButton.render(batch);
                font.draw(batch, mainTowerPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 365);
            } else {
                mainTowerButtonTransparent.render(batch);
                font.draw(batch, mainTowerPrice, Gdx.graphics.getWidth() - 185, Gdx.graphics.getHeight() - 365);
            }
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

        // add and update dynamite counter and price
        font.draw(batch, dynamiteStringPrice, 115, Gdx.graphics.getHeight() - 285);
        font.draw(batch, dynamiteString, 115, Gdx.graphics.getHeight() - 315);

        if (haveRightTower) {
            batch.draw(rightTowerBody, x, 0);
        }
        if (haveLeftTower) {
            batch.draw(leftTowerBody, x, 0);
        }
        if (haveRightTurret) {
            batch.draw(rightTurret, x, 0);
        }
        if (haveLeftTurret) {
            batch.draw(leftTurret, x, 0);
        }
        if (haveMainTower) {
            batch.draw(mainTower, x, 0);
        }


        batch.end();

        Vector3 mPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mPos);

        // add hover functionality to buttons
        clickerButton.logic(mPos);
        dungeonButton.logic(mPos);
        minerButton.logic(mPos);
        mainMenuButton.logic(mPos);
        pickaxeButton.logic(mPos);
        ponyButton.logic(mPos);
        dynamiteButton.logic(mPos);

        if (!haveRightTower) {
            rightTowerButton.logic(mPos);
        }
        if (!haveLeftTower) {
            leftTowerButton.logic(mPos);
        }
        if (!haveRightTurret) {
            rightTurretButton.logic(mPos);
        }
        if (!haveLeftTurret) {
            leftTurretButton.logic(mPos);
        }
        if (!haveMainTower) {
            mainTowerButton.logic(mPos);
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

    private void increaseGold() {
        goldCounter ++;

        goldString = "Gold: " + String.valueOf(goldCounter);
    }

    private void buyUpgrade(int upgrade) {

        if (goldCounter >= prices[upgrade]) {

            goldCounter -= prices[upgrade];
            upgrades[upgrade] ++;

            switch (upgrade) {

                case pickaxe:
                    pickaxeString = "Pickaxe Upgrades: " + upgrades[upgrade];
                    break;

                case miner:

                    minerString = "Miners: " + upgrades[upgrade];
                    break;

                case pony:

                    ponyString = "Ponies: " + upgrades[upgrade];
                    break;

                case dynamite:

                    dynamiteString = "Dynamite: " + upgrades[upgrade];
                    break;

            }

            goldString = "Gold: " + goldCounter;

        }

        if (upgrades[upgrade] > 0) {

            switch (upgrade) {
                case miner:
                    minerPrice += minerPrice * 0.6;
                    minerStringPrice = "Price: " + minerPrice;
                    prices[miner] = minerPrice;
                    break;

                case pickaxe:
                    pickaxePrice += pickaxePrice * 0.6;
                    pickaxeStringPrice = "Price: " + pickaxePrice;
                    prices[pickaxe] = pickaxePrice;
                    break;

                case pony:
                    ponyPrice += ponyPrice * 0.6;
                    ponyStringPrice = "Price: " + ponyPrice;
                    prices[pony] = ponyPrice;
                    break;

                case dynamite:
                    dynamitePrice += dynamitePrice * 0.6;
                    dynamiteStringPrice = "Price: " + dynamitePrice;
                    prices[dynamite] = dynamitePrice;
                    break;
            }

            gps += upgradeEffects[upgrade];
            gpsString = "Gold per Second: " + gps;

        }

    }


    private void buildRightTower() {

        if (goldCounter >= 1000 && rightTowerBlueprint) {
            haveRightTower = true;

        }
    }

    private void buildLeftTower() {
        if (goldCounter >= 2000 && leftTowerBlueprint) {
            haveLeftTower = true;
        }
    }

    private void buildRightTurret() {
        if (goldCounter >= 3000 && rightTurretBlueprint) {
            haveRightTurret = true;
        }
    }

    private void buildLeftTurret() {
        if (goldCounter >= 4000 && leftTurretBlueprint) {
            haveLeftTurret = true;
        }
    }

    private void buildMainTower() {
        if (goldCounter >= 5000 && mainTowerBlueprint) {
            haveMainTower = true;
        }
    }


    private void sendToDungeon() {
        saveToFile();

        getGame().setScreen(new Dungeon(getGame()));
        this.dispose();

    }

    private void sendToMainMenu() {

        saveToFile();

        getGame().setScreen(new Menu(getGame()));
        this.dispose();

    }

    private void updateGold() {

        goldCounter = goldCounter + gps;
        goldString = "Gold: " + String.valueOf(goldCounter);

    }

    private void saveToFile() {
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

            properties.setProperty("haveRightTower", String.valueOf(haveRightTower));
            properties.setProperty("haveLeftTower", String.valueOf(haveLeftTower));
            properties.setProperty("haveRightTurret", String.valueOf(haveRightTurret));
            properties.setProperty("haveLeftTurret", String.valueOf(haveLeftTurret));
            properties.setProperty("haveMainTower", String.valueOf(haveMainTower));

            File file = new File("save.properties");
            System.out.println(file.getAbsolutePath());
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut,"Ludum Dare 41 Save" );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
