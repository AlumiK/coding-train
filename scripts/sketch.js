let angle = 0;
const size = 135;
let cols;
let rows;
const diameter = size - 10;
let horizontal_circles =[];
let vertical_circles =[];
let curves = [];


function setup() {
    createCanvas(810, 810);
    noFill();
    colorMode(HSB);
    cols = width / size - 1;
    rows = height / size - 1;
    calculateColorSet();
    makeCurves();
    makeHorizontalCircles();
    makeVerticalCircles();
}

function draw() {
    background(0);
    for (let circle of horizontal_circles) {
        circle.next();
        circle.show();
    }
    for (let circle of vertical_circles) {
        circle.next();
        circle.show();
    }
    for (let i = 0; i < cols; i++) {
        for (let j = 0; j < rows; j++) {
            let index = i * rows + j;
            curves[index].addPoint(
                horizontal_circles[i].getPosition(),
                vertical_circles[j].getPosition()
            );
            curves[index].show();
            curves[index].drawPoint();
        }
    }
    increseAngle();
}

function calculateColorSet() {
    
}

function makeCurves() {
    for (let i = 0; i < cols; i++) {
        for (let j = 0; j < rows; j++) {
            curves.push(new Curve());
        }
    }
}

function makeHorizontalCircles() {
    for (let i = 0; i < cols; i++) {
        let center_x = i * size + size * 3 / 2;
        let center_y = size / 2;
        horizontal_circles.push(new Circle(center_x, center_y, diameter, i + 1, HORIZONTAL));
    }
}

function makeVerticalCircles() {
    for (let i = 0; i < rows; i++) {
        let center_x = size / 2;
        let center_y = i * size + size * 3 / 2;
        vertical_circles.push(new Circle(center_x, center_y, diameter, i + 1, VERTICAL));
    }
}

function increseAngle() {
    angle -= 0.007;
    if (angle < -TWO_PI) {
        angle = 0;
        for (let curve of curves) {
            curve.reset();
        }
    }
}
