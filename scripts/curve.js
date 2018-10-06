class Curve {
    constructor() {
        this.path = [];
    }

    addPoint(x, y) {
        this.path.push(createVector(x, y));
    }

    drawPoint() {
        stroke(255);
        strokeWeight(6);
        let current_point = this.path[this.path.length - 1];
        point(current_point.x, current_point.y);
    }

    show() {
        stroke(255);
        strokeWeight(2);
        beginShape();
        for (let p of this.path) {
            vertex(p.x, p.y);
        }
        endShape();
    }

    reset() {
        this.path = [];
    }
}
