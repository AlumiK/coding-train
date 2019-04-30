import processing.core.*;

class Cube {
    private PApplet mSketch;
    private Cubie[] mCubies;
    private boolean mShuffling;
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

    void move(int x, int y, int z, int dir) {
        mMove = new Move(this, x, y, z, dir, Constants.SPEED);
        mMove.start();
    }

    boolean isMoving() {
        return mMove != null && mMove.isRunning();
    }

    boolean isShuffling() {
        return mShuffling;
    }

    void startShuffle() {
        mShuffling = true;
    }

    void shuffle() {

    }

    void update() {
        mMove.update();
    }
}
