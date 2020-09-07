package cn.alumik.rubikscube;

import peasy.*;
import processing.core.*;

public class RubiksCube extends PApplet {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final float CAM_DISTANCE = 400;
    static final int BG_COLOR = 51;

    final private Cube mCube = new Cube(this);

    public static void main(String[] args) {
        PApplet.main(RubiksCube.class);
    }

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT, P3D);
        smooth(8);
    }

    @Override
    public void setup() {
        new PeasyCam(this, CAM_DISTANCE);
    }

    @Override
    public void draw() {
        background(BG_COLOR);
        mCube.update();
        mCube.show();
    }

    @Override
    public void keyPressed() {
        if (key == ' ') {
            mCube.shuffle();
        } else if (!mCube.isMoving() && !mCube.isShuffling()) {
            mCube.move(key);
        }
    }
}
