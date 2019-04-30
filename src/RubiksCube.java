import peasy.*;
import processing.core.*;

public class RubiksCube extends PApplet {
    private Cube mCube;
    private Move mCurrentMove;

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
        if (mCurrentMove != null) {
            mCurrentMove.update();
        }
        mCube.show(mCurrentMove);
    }

    @Override
    public void keyPressed() {
        if (mCurrentMove == null || !mCurrentMove.isRunning()) {
            switch (key) {
                case 'u':
                    makeMove(0, -1, 0, 1);
                    break;
                case 'U':
                    makeMove(0, -1, 0, -1);
                    break;
                case 'd':
                    makeMove(0, 1, 0, -1);
                    break;
                case 'D':
                    makeMove(0, 1, 0, 1);
                    break;
                case 'l':
                    makeMove(-1, 0, 0, 1);
                    break;
                case 'L':
                    makeMove(-1, 0, 0, -1);
                    break;
                case 'r':
                    makeMove(1, 0, 0, -1);
                    break;
                case 'R':
                    makeMove(1, 0, 0, 1);
                    break;
                case 'f':
                    makeMove(0, 0, 1, -1);
                    break;
                case 'F':
                    makeMove(0, 0, 1, 1);
                    break;
                case 'b':
                    makeMove(0, 0, -1, 1);
                    break;
                case 'B':
                    makeMove(0, 0, -1, -1);
                    break;
            }
        }
    }

    private void makeMove(int x, int y, int z, int dir) {
        mCurrentMove = new Move(mCube, x, y, z, dir, Constants.SPEED);
        mCurrentMove.start();
    }
}
