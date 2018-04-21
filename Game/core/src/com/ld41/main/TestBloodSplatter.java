package com.ld41.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class TestBloodSplatter {

    int x;
    int y;
    int angle;
    float duration;
    int count;
    int lifetime;
    float delta;
    boolean anim;
    ArrayList<TestBlood> bloodList;

    public TestBloodSplatter(int x, int y, int angle, int count, int lifetime) {

        this.angle = angle;
        this.duration = 0.25f;
        this.count = count;
        this.lifetime = lifetime;

        delta = 0;

        this.bloodList = new ArrayList<TestBlood>();

        //float startingAngle = this.angle - 45;
        //float increment = 90 / this.count;

        float lowerBound = this.angle - 30;
        float upperBound = this.angle + 30;
        float increment = 60 / this.count;

        for (int i = 0; i < count; i++) {

            float offset =  lowerBound + (1 + (increment * i));
            offset = MathUtils.clamp(offset, -30, 30);

            bloodList.add(new TestBlood(x, y, this.angle, offset));

        }

        anim = true;

    }

    public void render(SpriteBatch batch) {

        this.delta += Gdx.graphics.getDeltaTime();

        if (delta > this.lifetime) {

            ArrayList<TestBlood> toRemove = new ArrayList<TestBlood>();

            for (TestBlood blood : bloodList) {

                toRemove.add(blood);

            }

            bloodList.removeAll(toRemove);

        }

        if (this.anim) {

            if (this.delta > this.duration) {

                this.anim = false;

            } else {

                for (TestBlood blood : this.bloodList) {

                    double xSpeed = Math.toDegrees(Math.cos(Math.toRadians(blood.angle + blood.offset)) / 5);
                    double ySpeed = Math.toDegrees(Math.sin(Math.toRadians(blood.angle + blood.offset)) / 5);
                    int dx = blood.x + (int) Math.round(xSpeed);
                    int dy = blood.y + (int) Math.round(ySpeed);

                    blood.x = dx;
                    blood.y = dy;

                    batch.draw(blood.texture, blood.x, blood.y);

                }

            }

        } else {

            for (TestBlood blood : bloodList) {

                batch.draw(blood.texture, blood.x, blood.y);

            }

        }

    }

}
