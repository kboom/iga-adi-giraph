set datafile separator ","
set output 'weak-heat.png'
set term png size 800, 800
set lmargin at screen 0.12
set rmargin at screen 0.88
set bmargin at screen 0.12
set tmargin at screen 0.88

set xlabel 'Threads T [-]'
set ylabel 'Elements per thread E/T [-]' offset -2

set size ratio 1

set autoscale fix
set view map
set grid

plot \
    "processed3.csv" using 3:($8==9437000?$16:1/0):xtic(3) with points pointtype 1 pointsize 2 linewidth 3 notitle