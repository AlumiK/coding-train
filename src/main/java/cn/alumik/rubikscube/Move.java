package cn.alumik.rubikscube;

import processing.core.*;

class Move {
    static final char[] ALL_MOVES = {'u', 'U', 'd', 'D', 'l', 'L', 'r', 'R', 'b', 'B', 'f', 'F'};
    static final float SPEED = (float) 0.1;

    private PVector mPos;
    private int mDir;
    private boolean mRunning = true;
    private float mSpeed;
    private float mAngle;
    private Cube mCube;

    Move(Cube cube, int x, int y, int z, int dir) {
        mPos = new PVector(x, y, z);
        mDir = dir;
        mSpeed = SPEED;
        mCube = cube;
    }

    void start() {
        mRunning = true;
    }

    void update() {
        if (mRunning) {
            mAngle += mDir * mSpeed;
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
