set datafile separator ","
set output 'strong-scalability-lines.png'
set term png size 800, 800
set tmargin 4
set lmargin 10
set bmargin 5

set logscale x 2
set logscale y 2

set xlabel 'Threads [-]'
set ylabel 'Weak speedup [-]'

set grid
set key left top
set key box lt -1 lw 2 width 1 height 1 opaque

set style line 1 lc rgb 'red' pt 5 ps 2   # square
set style line 2 lc rgb 'blue' pt 5 ps 2  # circle
set style line 3 lc rgb 'green' pt 5 ps 2  # circle
set style line 4 lc rgb 'purple' pt 5 ps 2  # circle
set style line 5 lc rgb 'brown' pt 5 ps 2  # circle

tit(e) = sprintf("N = %d^{2}", e)

f(x) = x

plot \
    f(x) with lines lc rgb 'gray' lw 2 title 'Linear speedup', \
    "weak-scalability.csv" using 3:($1==12288?$15:1/0):xtic($3) with points ls 1 title tit(12288), \
    "weak-scalability.csv" using 3:($1==6144?$15:1/0):xtic($3) with points ls 2 title tit(6144), \
    "weak-scalability.csv" using 3:($1==3072?$15:1/0):xtic($3) with points ls 3 title tit(3072), \
    "weak-scalability.csv" using 3:($1==1536?$15:1/0):xtic($3) with points ls 4 title tit(1536), \
    "weak-scalability.csv" using 3:($1==768?$15:1/0):xtic($3) with points ls 5 title tit(768)