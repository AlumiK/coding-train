import processing.core.*;

class Cube {
    private PApplet mSketch;
    private Cubie[] mCubies;
    private boolean mShuffling;
    private int mShuffleSteps;
    private Move mMove;

    Cube(PApplet sketch) {
        mSketch = sketch;
        mCubies = new Cubie[27];
        mShuffling = false;
        int index = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    mCubies[index] = new Cubie(mSketch, x, y, z);
                    index++;
                }
            }
        }
    }

    void show() {
        for (Cubie cubie : mCubies) {
            mSketch.pushMatrix();
            if (mMove != null && mMove.isRunning()) {
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
        mShuffling = true;
        mShuffleSteps = 0;
    }

    void update() {
        mMove.update();
        if (mShuffling && !isMoving()) {
            if (mShuffleSteps < Constants.SHUFFLE_STEPS) {
                makeMove(Constants.ALL_MOVES[PApplet.floor(mSketch.random(Constants.ALL_MOVES.length))]);
                mShuffleSteps++;
            } else {
                mShuffling = false;
            }
        }
    }

    void makeMove(char key) {
        switch (key) {
            case 'u':
                move(0, -1, 0, 1);
                break;
            case 'U':
                move(0, -1, 0, -1);
                break;
            case 'd':
                move(0, 1, 0, -1);
                break;
            case 'D':
                move(0, 1, 0, 1);
                break;
            case 'l':
                move(-1, 0, 0, 1);
                break;
            case 'L':
                move(-1, 0, 0, -1);
                break;
            case 'r':
                move(1, 0, 0, -1);
                break;
            case 'R':
                move(1, 0, 0, 1);
                break;
            case 'f':
                move(0, 0, 1, -1);
                break;
            case 'F':
                move(0, 0, 1, 1);
                break;
            case 'b':
                move(0, 0, -1, 1);
                break;
            case 'B':
                move(0, 0, -1, -1);
                break;
        }
    }

    private void move(int x, int y, int z, int dir) {
        mMove = new Move(this, x, y, z, dir, Constants.SPEED);
        mMove.start();
    }
}
