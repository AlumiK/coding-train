import processing.core.*;

class Cube {
    private PApplet mSketch;
    private Cubie[] mCubies;

    Cube(PApplet sketch) {
        mSketch = sketch;
        mCubies = new Cubie[27];
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

    void show(Move move) {
        for (Cubie cubie : mCubies) {
            mSketch.pushMatrix();
            if (move != null && move.isRunning()) {
                PVector movePos = move.getPos();
                PVector cubiePos = cubie.getPos();
                if (movePos.x != 0 && movePos.x == cubiePos.x) {
                    mSketch.rotateX(move.getAngle());
                } else if (movePos.y != 0 && movePos.y == cubiePos.y) {
                    mSketch.rotateY(move.getAngle());
                } else if (movePos.z != 0 && movePos.z == cubiePos.z) {
                    mSketch.rotateZ(move.getAngle());
                }
            }
            cubie.show();
            mSketch.popMatrix();
        }
    }

    void applyMove(Move move) {
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
}
