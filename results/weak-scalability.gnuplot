set datafile separator ","
set output 'weak-scalability.png'
set term png size 800, 800
set tmargin 4
set lmargin 10
set bmargin 5

set xlabel 'Threads [-]'
set ylabel 'Weak speedup [-]'

set grid
set xrange [1:256]
set xtics (1,16,64,256)
set yrange [1:256]
set ytics (1,16,32,48,64,96,128,192,256)

set key left top
set key box lt -1 lw 2 width 1 height 1 opaque


set style line 1 lc rgb 'red' pt 5 ps 2   # square
set style line 2 lc rgb 'blue' pt 7 ps 2  # circle

o1(x) = a1*x
fit o1(x) 'weak-scalability.csv' using 3:($10==590000?$25:1/0) via a1
o1title = sprintf("O(N) = %.2fN (+/- %.2f\%)", a1, a1_err/a1*100)

o2(x) = a2*x
fit o2(x) 'weak-scalability.csv' using 3:($10==147000?$25:1/0) via a2
o2title = sprintf("O(N) = %.2fN (+/- %.2f\%)", a2, a2_err/a2*100)

tit(e) = sprintf("%d*10^{3} E/T", e/1000)


f(x) = x

plot \
    "weak-scalability.csv" using 3:($10==590000?$25:1/0):xtic($3) with points ls 1 title tit(590000), \
    o1(x) with lines lc rgb 'red' lw 2 title o1title, \
    "weak-scalability.csv" using 3:($10==147000?$25:1/0):xtic($3) with points ls 2 title tit(147000), \
    o2(x) with lines lc rgb 'blue' lw 2 title o2title, \
    f(x) with lines lc rgb 'green' lw 2 title 'Ideal speedup'