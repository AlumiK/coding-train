# #116 Lissajous Curve Table

Named after Jules Antoine Lissajous, a Lissajous curve /ˈlɪsəʒuː/, also known as Lissajous figure or Bowditch curve /ˈbaʊdɪtʃ/, is the graph of a system of parametric equations which describe complex harmonic motion.

This family of curves was investigated by Nathaniel Bowditch in 1815, and later in more detail by Jules Antoine Lissajous in 1857.

## The Coding Train Video Links

[Coding Challenge #116: Lissajous Curve Table](https://youtu.be/--6eyLO78CY)

[Coding Challenge #116 Continued: Lissajous Curve Table in p5.js](https://youtu.be/glDU8Nsyidg)

## Math Definition

A Lissajous curve is defined by the following parametric equations:

![\begin{cases}x(\theta )=a\sin(\theta )\\y(\theta )=b\sin(n\theta +\phi )\end{cases}](https://render.githubusercontent.com/render/math?math=%5Cdisplaystyle+%5Cbegin%7Bcases%7Dx%28%5Ctheta+%29%3Da%5Csin%28%5Ctheta+%29%5C%5Cy%28%5Ctheta+%29%3Db%5Csin%28n%5Ctheta+%2B%5Cphi+%29%5Cend%7Bcases%7D)

where ![0\leq \phi \leq {\frac {\pi }{2}}, n\geq 1](https://render.githubusercontent.com/render/math?math=%5Cdisplaystyle+0%5Cleq+%5Cphi+%5Cleq+%7B%5Cfrac+%7B%5Cpi+%7D%7B2%7D%7D%2C+n%5Cgeq+1).

When ![n={\frac {q}{p}}](https://render.githubusercontent.com/render/math?math=%5Cdisplaystyle+n%3D%7B%5Cfrac+%7Bq%7D%7Bp%7D%7D) is rational, the parametric equations can be written as:

![\begin{cases}x(\theta )=a\sin(p\theta )\\y(\theta )=b\sin(q\theta +\phi )\\0\leq \theta \leq 2\pi \end{cases}](https://render.githubusercontent.com/render/math?math=%5Cdisplaystyle+%5Cbegin%7Bcases%7Dx%28%5Ctheta+%29%3Da%5Csin%28p%5Ctheta+%29%5C%5Cy%28%5Ctheta+%29%3Db%5Csin%28q%5Ctheta+%2B%5Cphi+%29%5C%5C0%5Cleq+%5Ctheta+%5Cleq+2%5Cpi+%5Cend%7Bcases%7D)

where ![0\leq \phi \leq {\frac {\pi }{2p}}](https://render.githubusercontent.com/render/math?math=%5Cdisplaystyle+0%5Cleq+%5Cphi+%5Cleq+%7B%5Cfrac+%7B%5Cpi+%7D%7B2p%7D%7D).

## Demo

https://pages.alumik.cn/coding-train/coding_challenges/116_lissajous_curve_table/
