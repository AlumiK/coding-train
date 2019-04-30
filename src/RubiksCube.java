import peasy.*;
import processing.core.*;

public class RubiksCube extends PApplet {
    private Cube mCube;

    public static void main(String[] args) {
        PApplet.main("RubiksCube");
    }

    @Override
    public void settings() {
        size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, P3D);
        smooth(8);
    }

    @Override
    public void setup() {
        new PeasyCam(this, Constants.CAM_DISTANCE);
        mCube = new Cube(this);
    }

    @Override
    public void draw() {
        background(Constants.BG_COLOR);
        if (mCube.isMoving() || mCube.isShuffling()) {
            mCube.update();
        }
        mCube.show();
    }

    @Override
    public void keyPressed() {
        if (!mCube.isMoving() && !mCube.isShuffling()) {
            switch (key) {
                case 'u':
                    mCube.move(0, -1, 0, 1);
                    break;
                case 'U':
                    mCube.move(0, -1, 0, -1);
                    break;
                case 'd':
                    mCube.move(0, 1, 0, -1);
                    break;
                case 'D':
                    mCube.move(0, 1, 0, 1);
                    break;
                case 'l':
                    mCube.move(-1, 0, 0, 1);
                    break;
                case 'L':
                    mCube.move(-1, 0, 0, -1);
                    break;
                case 'r':
                    mCube.move(1, 0, 0, -1);
                    break;
                case 'R':
                    mCube.move(1, 0, 0, 1);
                    break;
                case 'f':
                    mCube.move(0, 0, 1, -1);
                    break;
                case 'F':
                    mCube.move(0, 0, 1, 1);
                    break;
                case 'b':
                    mCube.move(0, 0, -1, 1);
                    break;
                case 'B':
                    mCube.move(0, 0, -1, -1);
                    break;
            }
        }
    }
}
