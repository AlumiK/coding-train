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
        if (key == ' ') {
            mCube.shuffle();
        } else if (!mCube.isMoving() && !mCube.isShuffling()) {
            mCube.makeMove(key);
        }
    }
}
