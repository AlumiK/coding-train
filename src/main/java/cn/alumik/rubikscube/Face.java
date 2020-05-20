package cn.alumik.rubikscube;

import processing.core.*;

class Face {
    private PApplet mSketch;
    private PVector mNormal;
    private int mColor;

    Face(PApplet sketch, PVector normal, int color) {
        mSketch = sketch;
        mNormal = normal;
        mColor = color;
    }

    void show() {
        mSketch.fill(mColor);
        mSketch.noStroke();
        mSketch.rectMode(PConstants.CENTER);
        mSketch.pushMatrix();
        mSketch.translate(
                (float) (0.5 * mNormal.x * Constants.BOX_SIZE),
                (float) (0.5 * mNormal.y * Constants.BOX_SIZE),
                (float) (0.5 * mNormal.z * Constants.BOX_SIZE));
        if (mNormal.x != 0) {
            mSketch.rotateY(PConstants.HALF_PI);
        } else if (mNormal.y != 0) {
            mSketch.rotateX(PConstants.HALF_PI);
        }
        mSketch.rect(
                0,
                0,
                Constants.BOX_SIZE - Constants.BOX_RADIUS,
                Constants.BOX_SIZE - Constants.BOX_RADIUS,
                Constants.BOX_RADIUS);
        mSketch.popMatrix();
    }

    void rotateX(int dir) {
        float angle = dir * PConstants.HALF_PI;
        PVector normal = new PVector();
        normal.y = PApplet.round(mNormal.y * PApplet.cos(angle) - mNormal.z * PApplet.sin(angle));
        normal.z = PApplet.round(mNormal.y * PApplet.sin(angle) + mNormal.z * PApplet.cos(angle));
        normal.x = PApplet.round(mNormal.x);
        mNormal = normal;
    }

    void rotateY(int dir) {
        float angle = dir * PConstants.HALF_PI;
        PVector normal = new PVector();
        normal.x = PApplet.round(mNormal.x * PApplet.cos(angle) + mNormal.z * PApplet.sin(angle));
        normal.z = PApplet.round(-mNormal.x * PApplet.sin(angle) + mNormal.z * PApplet.cos(angle));
        normal.y = PApplet.round(mNormal.y);
        mNormal = normal;
    }

    void rotateZ(int dir) {
        float angle = dir * PConstants.HALF_PI;
        PVector normal = new PVector();
        normal.x = PApplet.round(mNormal.x * PApplet.cos(angle) - mNormal.y * PApplet.sin(angle));
        normal.y = PApplet.round(mNormal.x * PApplet.sin(angle) + mNormal.y * PApplet.cos(angle));
        normal.z = PApplet.round(mNormal.z);
        mNormal = normal;
    }
}
