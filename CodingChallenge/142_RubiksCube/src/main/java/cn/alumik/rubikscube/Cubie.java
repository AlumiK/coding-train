package cn.alumik.rubikscube;

import processing.core.*;

import java.util.ArrayList;

class Cubie {

    static final float SIZE = 50;
    static final float RADIUS = 5;

    final private PApplet mSketch;
    final private PMatrix3D mMatrix = new PMatrix3D();
    final private ArrayList<Face> mFaces;
    private PVector mPos;

    Cubie(PApplet sketch, int x, int y, int z) {
        mSketch = sketch;
        update(x, y, z);

        mFaces = new ArrayList<>();
        if (mPos.x == -1) {
            mFaces.add(new Face(mSketch, new PVector(-1, 0, 0), Face.COLOR_LEFT));
            mFaces.add(new Face(mSketch, new PVector(1, 0, 0), Face.COLOR_BASE));
        } else if (mPos.x == 1) {
            mFaces.add(new Face(mSketch, new PVector(1, 0, 0), Face.COLOR_RIGHT));
            mFaces.add(new Face(mSketch, new PVector(-1, 0, 0), Face.COLOR_BASE));
        } else {
            mFaces.add(new Face(mSketch, new PVector(1, 0, 0), Face.COLOR_BASE));
            mFaces.add(new Face(mSketch, new PVector(-1, 0, 0), Face.COLOR_BASE));
        }
        if (mPos.y == -1) {
            mFaces.add(new Face(mSketch, new PVector(0, -1, 0), Face.COLOR_UP));
            mFaces.add(new Face(mSketch, new PVector(0, 1, 0), Face.COLOR_BASE));
        } else if (mPos.y == 1) {
            mFaces.add(new Face(mSketch, new PVector(0, 1, 0), Face.COLOR_DOWN));
            mFaces.add(new Face(mSketch, new PVector(0, -1, 0), Face.COLOR_BASE));
        } else {
            mFaces.add(new Face(mSketch, new PVector(0, 1, 0), Face.COLOR_BASE));
            mFaces.add(new Face(mSketch, new PVector(0, -1, 0), Face.COLOR_BASE));
        }
        if (mPos.z == -1) {
            mFaces.add(new Face(mSketch, new PVector(0, 0, -1), Face.COLOR_BACK));
            mFaces.add(new Face(mSketch, new PVector(0, 0, 1), Face.COLOR_BASE));
        } else if (mPos.z == 1) {
            mFaces.add(new Face(mSketch, new PVector(0, 0, 1), Face.COLOR_FRONT));
            mFaces.add(new Face(mSketch, new PVector(0, 0, -1), Face.COLOR_BASE));
        } else {
            mFaces.add(new Face(mSketch, new PVector(0, 0, 1), Face.COLOR_BASE));
            mFaces.add(new Face(mSketch, new PVector(0, 0, -1), Face.COLOR_BASE));
        }
    }

    void show() {
        mSketch.fill(Face.COLOR_BASE);
        mSketch.noStroke();
        mSketch.pushMatrix();
        mSketch.applyMatrix(mMatrix);
        mSketch.box(SIZE - 0.01f);
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
        mMatrix.translate(x * SIZE, y * SIZE, z * SIZE);
        mPos = new PVector(x, y, z);
    }
}
