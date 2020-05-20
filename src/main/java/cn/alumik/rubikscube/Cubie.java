package cn.alumik.rubikscube;

import processing.core.*;

import java.util.ArrayList;

class Cubie {
    private PApplet mSketch;
    private PMatrix3D mMatrix;
    private PVector mPos;
    private ArrayList<Face> mFaces;

    Cubie(PApplet sketch, int x, int y, int z) {
        mSketch = sketch;
        mMatrix = new PMatrix3D();
        mMatrix.translate(x * Constants.BOX_SIZE, y * Constants.BOX_SIZE, z * Constants.BOX_SIZE);
        mPos = new PVector(x, y, z);

        mFaces = new ArrayList<>();
        if (mPos.x == -1) {
            mFaces.add(new Face(mSketch, new PVector(-1, 0, 0), Constants.COLOR_LEFT));
            mFaces.add(new Face(mSketch, new PVector(1, 0, 0), Constants.COLOR_BASE));
        } else if (mPos.x == 1) {
            mFaces.add(new Face(mSketch, new PVector(1, 0, 0), Constants.COLOR_RIGHT));
            mFaces.add(new Face(mSketch, new PVector(-1, 0, 0), Constants.COLOR_BASE));
        } else {
            mFaces.add(new Face(mSketch, new PVector(1, 0, 0), Constants.COLOR_BASE));
            mFaces.add(new Face(mSketch, new PVector(-1, 0, 0), Constants.COLOR_BASE));
        }
        if (mPos.y == -1) {
            mFaces.add(new Face(mSketch, new PVector(0, -1, 0), Constants.COLOR_UP));
            mFaces.add(new Face(mSketch, new PVector(0, 1, 0), Constants.COLOR_BASE));
        } else if (mPos.y == 1) {
            mFaces.add(new Face(mSketch, new PVector(0, 1, 0), Constants.COLOR_DOWN));
            mFaces.add(new Face(mSketch, new PVector(0, -1, 0), Constants.COLOR_BASE));
        } else {
            mFaces.add(new Face(mSketch, new PVector(0, 1, 0), Constants.COLOR_BASE));
            mFaces.add(new Face(mSketch, new PVector(0, -1, 0), Constants.COLOR_BASE));
        }
        if (mPos.z == -1) {
            mFaces.add(new Face(mSketch, new PVector(0, 0, -1), Constants.COLOR_BACK));
            mFaces.add(new Face(mSketch, new PVector(0, 0, 1), Constants.COLOR_BASE));
        } else if (mPos.z == 1) {
            mFaces.add(new Face(mSketch, new PVector(0, 0, 1), Constants.COLOR_FRONT));
            mFaces.add(new Face(mSketch, new PVector(0, 0, -1), Constants.COLOR_BASE));
        } else {
            mFaces.add(new Face(mSketch, new PVector(0, 0, 1), Constants.COLOR_BASE));
            mFaces.add(new Face(mSketch, new PVector(0, 0, -1), Constants.COLOR_BASE));
        }
    }

    void show() {
        mSketch.fill(Constants.COLOR_BASE);
        mSketch.noStroke();
        mSketch.pushMatrix();
        mSketch.applyMatrix(mMatrix);
        mSketch.box(Constants.BOX_SIZE - (float) 0.01);
        for (Face face : mFaces) {
            face.show();
        }
        mSketch.popMatrix();
    }

    void rotateX(int dir) {
        PMatrix2D matrix = new PMatrix2D();
        matrix.rotate(dir * PConstants.HALF_PI);
        matrix.translate(mPos.y, mPos.z);
        for (Face face : mFaces) {
            face.rotateX(dir);
        }
        update(PApplet.round(mPos.x), PApplet.round(matrix.m02), PApplet.round(matrix.m12));
    }

    void rotateY(int dir) {
        PMatrix2D matrix = new PMatrix2D();
        matrix.rotate(-dir * PConstants.HALF_PI);
        matrix.translate(mPos.x, mPos.z);
        for (Face face : mFaces) {
            face.rotateY(dir);
        }
        update(PApplet.round(matrix.m02), PApplet.round(mPos.y), PApplet.round(matrix.m12));
    }

    void rotateZ(int dir) {
        PMatrix2D matrix = new PMatrix2D();
        matrix.rotate(dir * PConstants.HALF_PI);
        matrix.translate(mPos.x, mPos.y);
        for (Face face : mFaces) {
            face.rotateZ(dir);
        }
        update(PApplet.round(matrix.m02), PApplet.round(matrix.m12), PApplet.round(mPos.z));
    }

    PVector getPos() {
        return mPos.copy();
    }

    private void update(int x, int y, int z) {
        mMatrix.reset();
        mMatrix.translate(x * Constants.BOX_SIZE, y * Constants.BOX_SIZE, z * Constants.BOX_SIZE);
        mPos = new PVector(x, y, z);
    }
}
