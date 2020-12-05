const res = 10
let rows, cols
let field = []
let noise
let zoff = 0

function setup() {
    createCanvas(800, 600)
    rows = height / res + 1
    cols = width / res + 1
    noise = new OpenSimplexNoise(Date.now())
    for (let i = 0; i < cols; ++i) {
        tmp = []
        for (let j = 0; j < rows; ++j) {
            tmp.push(0)
        }
        field.push(tmp)
    }
}

function draw() {
    background(0)
    let xoff = 0
    for (let i = 0; i < cols; ++i) {
        xoff += 0.1
        let yoff = 0
        for (let j = 0; j < rows; ++j) {
            field[i][j] = float(noise.noise3D(xoff, yoff, zoff))
            yoff += 0.1
        }
    }
    zoff += 0.03;
    for (let i = 0; i < cols - 1; ++i) {
        for (let j = 0; j < rows - 1; ++j) {
            separateLine(i, j)
        }
    }
}

function getState(p, q, r, s) {
    return s + r * 2 + q * 4 + p * 8
}

function separateLine(i, j) {

    function _line(a, b) {
        line(a.x, a.y, b.x, b.y)
    }

    const x = i * res
    const y = j * res
    const half_res = 0.5 * res
    let a = createVector(x + half_res, y)
    let b = createVector(x + res, y + half_res)
    let c = createVector(x + half_res, y + res)
    let d = createVector(x, y + half_res)
    const state = getState(
        ceil(field[i][j]),
        ceil(field[i + 1][j]),
        ceil(field[i + 1][j + 1]),
        ceil(field[i][j + 1])
    )
    stroke(255)
    strokeWeight(1)

    switch (state) {
        case 0:
            break
        case 1:
            _line(c, d)
            break
        case 2:
            _line(b, c)
            break
        case 3:
            _line(b, d)
            break
        case 4:
            _line(a, b)
            break
        case 5:
            _line(a, d)
            _line(b, c)
            break
        case 6:
            _line(a, c)
            break
        case 7:
            _line(a, d)
            break
        case 8:
            _line(a, d)
            break
        case 9:
            _line(a, c)
            break
        case 10:
            _line(a, b)
            _line(c, d)
            break
        case 11:
            _line(a, b)
            break
        case 12:
            _line(b, d)
            break
        case 13:
            _line(b, c)
            break
        case 14:
            _line(c, d)
            break
        case 15:
            break
    }
}
