package cn.alumik.rubikscube;

import processing.core.*;

class Face {

    static final int COLOR_BASE = 0;
    static final int COLOR_UP = 0xFFFFFFFF;
    static final int COLOR_DOWN = 0xFFFFFF00;
    static final int COLOR_LEFT = 0xFFFFA500;
    static final int COLOR_RIGHT = 0xFFFF0000;
    static final int COLOR_BACK = 0xFF0000FF;
    static final int COLOR_FRONT = 0xFF00FF00;

    final private PApplet mSketch;
    final private int mColor;
    private PVector mNormal;

    Face(PApplet sketch, PVector normal, int color) {
        mSketch = sketch;
        mColor = color;
        mNormal = normal;
    }

    void show() {
        mSketch.fill(mColor);
        mSketch.noStroke();
        mSketch.rectMode(PConstants.CENTER);
        mSketch.pushMatrix();
        mSketch.translate(
                0.5f * mNormal.x * Cubie.SIZE,
                0.5f * mNormal.y * Cubie.SIZE,
                0.5f * mNormal.z * Cubie.SIZE);
        if (mNormal.x != 0) {
            mSketch.rotateY(PConstants.HALF_PI);
        } else if (mNormal.y != 0) {
            mSketch.rotateX(PConstants.HALF_PI);
        }
        mSketch.rect(0, 0, Cubie.SIZE - Cubie.RADIUS, Cubie.SIZE - Cubie.RADIUS, Cubie.RADIUS);
        mSketch.popMatrix();
    }

    void rotateX(int dir) {
        final float angle = dir * PConstants.HALF_PI;
        final PVector normal = new PVector();
        normal.y = PApplet.round(mNormal.y * PApplet.cos(angle) - mNormal.z * PApplet.sin(angle));
        normal.z = PApplet.round(mNormal.y * PApplet.sin(angle) + mNormal.z * PApplet.cos(angle));
        normal.x = PApplet.round(mNormal.x);
        mNormal = normal;
    }

    void rotateY(int dir) {
        final float angle = dir * PConstants.HALF_PI;
        final PVector normal = new PVector();
        normal.x = PApplet.round(mNormal.x * PApplet.cos(angle) + mNormal.z * PApplet.sin(angle));
        normal.z = PApplet.round(-mNormal.x * PApplet.sin(angle) + mNormal.z * PApplet.cos(angle));
        normal.y = PApplet.round(mNormal.y);
        mNormal = normal;
    }

    void rotateZ(int dir) {
        final float angle = dir * PConstants.HALF_PI;
        final PVector normal = new PVector();
        normal.x = PApplet.round(mNormal.x * PApplet.cos(angle) - mNormal.y * PApplet.sin(angle));
        normal.y = PApplet.round(mNormal.x * PApplet.sin(angle) + mNormal.y * PApplet.cos(angle));
        normal.z = PApplet.round(mNormal.z);
        mNormal = normal;
    }
}
