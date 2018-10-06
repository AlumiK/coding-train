const HORIZONTAL = true;
const VERTICAL = false;

class Circle {
    constructor(x, y, diameter, speed, type, color) {
        this.center = createVector(x, y);
        this.diameter = diameter;
        this.speed = speed;
        this.type = type;
        this.color = color;
        this.p = createVector(
            (this.diameter / 2.0) * cos(angle * this.speed - HALF_PI) + this.center.x,
            (this.diameter / 2.0) * sin(angle * this.speed - HALF_PI) + this.center.y
        );
    }

    drawCircle() {
        stroke(this.color);
        strokeWeight(4);
        ellipse(this.center.x, this.center.y, this.diameter, this.diameter);
    }

    drawLine() {
        stroke(255, 0.1);
        strokeWeight(2);
        if (this.type === HORIZONTAL) {
            line(this.p.x, 0, this.p.x, height);
        } else {
            line(0, this.p.y, width, this.p.y);
        }
    }

    drawPoint() {
        stroke(255);
        strokeWeight(12);
        point(this.p.x, this.p.y);
    }

    drawSpeed() {
        noStroke();
        fill(255);
        text(this.speed, this.center.x, this.center.y);
        noFill();
    }

    next() {
        this.p = createVector(
            (this.diameter / 2.0) * cos(angle * this.speed - HALF_PI) + this.center.x,
            (this.diameter / 2.0) * sin(angle * this.speed - HALF_PI) + this.center.y
        );
    }

    getPosition() {
        if (this.type === HORIZONTAL) {
            return this.p.x;
        }
        return this.p.y;
    }

    show() {
        this.drawCircle();
        this.drawLine();
        this.drawPoint();
        this.drawSpeed();
    }
}
