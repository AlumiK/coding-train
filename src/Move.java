import processing.core.*;

class Move {
    private PVector mPos;
    private int mDir;
    private boolean mRunning;
    private float mSpeed;
    private float mAngle;
    private Cube mCube;

    Move(Cube cube, int x, int y, int z, int dir, float speed) {
        mPos = new PVector(x, y, z);
        mDir = dir;
        mRunning = false;
        mSpeed = speed;
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
                mCube.applyMove(this);
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
