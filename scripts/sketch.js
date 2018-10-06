let angle = 0;
const size = 135;
let cols;
let rows;
const diameter = size - 30;
let horizontal_circles = [];
let vertical_circles = [];
let curves = [];
let horizontal_circle_colors = [];
let vertical_circle_colors = [];
let curve_colors = [];


function setup() {
    createCanvas(810, 810);
    noFill();
    colorMode(HSB);
    textAlign(CENTER, CENTER);
    textSize(45);
    cols = width / size - 1;
    rows = height / size - 1;
    calculateColors();
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

function calculateColors() {
    horizontal_circle_colors = calculateCircleColors(cols);
    vertical_circle_colors = calculateCircleColors(rows);
    for (let i = 0; i < cols; i++) {
        for (let j = 0; j < rows; j++) {
            let mid_color = lerpColor(
                horizontal_circle_colors[i],
                vertical_circle_colors[j],
                0.5
            );
            curve_colors.push(color(
                hue(mid_color),
                50,
                100
            ));
        }
    }
}

function calculateCircleColors(count) {
    let hue_step = 360 / count;
    let circle_colors = [];
    for (let i = 1; i <= count; i++) {
        circle_colors.push(color(i * hue_step, 100, 100));
    }
    return circle_colors;
}

function makeCurves() {
    for (let i = 0; i < cols * rows; i++) {
        curves.push(new Curve(curve_colors[i]));
    }
}

function makeHorizontalCircles() {
    for (let i = 0; i < cols; i++) {
        let center_x = i * size + size * 3 / 2;
        let center_y = size / 2;
        horizontal_circles.push(new Circle(
            center_x,
            center_y,
            diameter,
            i + 1,
            HORIZONTAL,
            horizontal_circle_colors[i]
        ));
    }
}

function makeVerticalCircles() {
    for (let i = 0; i < rows; i++) {
        let center_x = size / 2;
        let center_y = i * size + size * 3 / 2;
        vertical_circles.push(new Circle(
            center_x,
            center_y,
            diameter,
            i + 1,
            VERTICAL,
            vertical_circle_colors[i]
        ));
    }
}

function increseAngle() {
    angle -= 0.01;
    if (angle < -TWO_PI) {
        angle = 0;
        for (let curve of curves) {
            curve.reset();
        }
    }
}
