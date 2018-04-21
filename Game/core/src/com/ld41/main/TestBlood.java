package com.ld41.main;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class TestBlood {

    int x;
    int y;
    float angle;
    float offset;
    Texture[] blood;
    Texture texture;
    Random r;

    public TestBlood(int x, int y, float angle, float offset) {

        this.x = x;
        this.y = y;
        this.angle = angle;
        this.offset = offset;

        System.out.println("Angle: " + angle + ", Offset: " + offset);

        r = new Random();

        blood = new Texture[3];

        for (int i = 0; i < 3; i++) {

            int j = i + 1;

            blood[i] = new Texture("test/blood" + j + ".png");

        }

        this.texture = blood[r.nextInt(2)];

    }

    public int getX() {

        return this.x;

    }

    public int getY() {

        return this.y;

    }

    public void setX(int x) {

        this.x = x;

    }

    public void setY(int y) {

        this.y = y;

    }

}
