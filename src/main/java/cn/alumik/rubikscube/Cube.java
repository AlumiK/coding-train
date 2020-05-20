package cn.alumik.rubikscube;

import processing.core.*;

class Cube {

    final private PApplet mSketch;
    final private Cubie[] mCubies = new Cubie[27];
    private boolean mShuffling;
    private Move mMove;

    Cube(PApplet sketch) {
        mSketch = sketch;
        int index = 0;
        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y <= 1; ++y) {
                for (int z = -1; z <= 1; ++z) {
                    mCubies[index++] = new Cubie(mSketch, x, y, z);
                }
            }
        }
    }

    void show() {
        for (Cubie cubie : mCubies) {
            mSketch.pushMatrix();
            if (isMoving()) {
                PVector movePos = mMove.getPos();
                PVector cubiePos = cubie.getPos();
                if (movePos.x != 0 && movePos.x == cubiePos.x) {
                    mSketch.rotateX(mMove.getAngle());
                } else if (movePos.y != 0 && movePos.y == cubiePos.y) {
                    mSketch.rotateY(mMove.getAngle());
                } else if (movePos.z != 0 && movePos.z == cubiePos.z) {
                    mSketch.rotateZ(mMove.getAngle());
                }
            }
            cubie.show();
            mSketch.popMatrix();
        }
    }

    void applyRotation(Move move) {
        PVector movePos = move.getPos();
        for (Cubie cubie : mCubies) {
            PVector cubiePos = cubie.getPos();
            if (movePos.x != 0 && movePos.x == cubiePos.x) {
                cubie.rotateX(move.getDir());
            } else if (movePos.y != 0 && movePos.y == cubiePos.y) {
                cubie.rotateY(move.getDir());
            } else if (movePos.z != 0 && movePos.z == cubiePos.z) {
                cubie.rotateZ(move.getDir());
            }
        }
    }

    boolean isMoving() {
        return mMove != null && mMove.isRunning();
    }

    boolean isShuffling() {
        return mShuffling;
    }

    void shuffle() {
        mShuffling = !mShuffling;
    }

    void update() {
        if (isMoving() || isShuffling()) {
            if (mShuffling && !isMoving()) {
                move(Move.ALL_MOVES[(int) mSketch.random(Move.ALL_MOVES.length)]);
            }
            mMove.update();
        }
    }

    void move(char key) {
        switch (key) {
            case 'u':
                mMove = new Move(this, 0, -1, 0, 1);
                break;
            case 'U':
                mMove = new Move(this, 0, -1, 0, -1);
                break;
            case 'd':
                mMove = new Move(this, 0, 1, 0, -1);
                break;
            case 'D':
                mMove = new Move(this, 0, 1, 0, 1);
                break;
            case 'l':
                mMove = new Move(this, -1, 0, 0, 1);
                break;
            case 'L':
                mMove = new Move(this, -1, 0, 0, -1);
                break;
            case 'r':
                mMove = new Move(this, 1, 0, 0, -1);
                break;
            case 'R':
                mMove = new Move(this, 1, 0, 0, 1);
                break;
            case 'f':
                mMove = new Move(this, 0, 0, 1, -1);
                break;
            case 'F':
                mMove = new Move(this, 0, 0, 1, 1);
                break;
            case 'b':
                mMove = new Move(this, 0, 0, -1, 1);
                break;
            case 'B':
                mMove = new Move(this, 0, 0, -1, -1);
                break;
        }
    }
}
