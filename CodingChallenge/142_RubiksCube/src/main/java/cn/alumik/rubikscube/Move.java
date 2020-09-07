package cn.alumik.rubikscube;

import processing.core.*;

class Move {

    static final char[] ALL_MOVES = {'u', 'U', 'd', 'D', 'l', 'L', 'r', 'R', 'b', 'B', 'f', 'F'};
    static final float SPEED = 0.1f;

    final private Cube mCube;
    final private PVector mPos;
    final private int mDir;
    private boolean mRunning = true;
    private float mAngle;

    Move(Cube cube, int x, int y, int z, int dir) {
        mCube = cube;
        mPos = new PVector(x, y, z);
        mDir = dir;
    }

    void update() {
        if (mRunning) {
            mAngle += mDir * SPEED;
            if (PApplet.abs(mAngle) > PConstants.HALF_PI) {
                mRunning = false;
                mCube.applyRotation(this);
            }
        }
    }

    PVector getPos() {
        return mPos.copy();
    }

    int getDir() {
        return mDir;
    }

    float getAngle() {
        return mAngle;
    }

    boolean isRunning() {
        return mRunning;
    }
}
