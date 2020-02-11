set datafile separator ","
set output '3d.png'
set term png size 800, 800
set lmargin at screen 0.12
set rmargin at screen 0.88
set bmargin at screen 0.12
set tmargin at screen 0.88

set xlabel 'Threads T [-]'
set ylabel 'Elements per thread U=E/T [-]' offset -2

set size ratio 1


set logscale y
set format y "10^{%T}"
set autoscale fix
set view map
set grid
set palette rgbformulae 7,5,15
set colorbox vertical default
set cblabel 'Strong speedup'

plot \
    "processed2.csv" using 3:($1==12288?$8:1/0) with lines linewidth 3 title '12288^2', \
    "processed2.csv" using 3:8:($1==12288?$13:1/0):xtic(3) with points pointtype 1 pointsize 0.1 palette linewidth 20 notitle, \
    "processed2.csv" using 3:($1==6144?$8:1/0) with lines linewidth 3 title '6144^2', \
    "processed2.csv" using 3:8:($1==6144?$13:1/0):xtic(3) with points pointtype 1 pointsize 0.1 palette linewidth 20 notitle, \
    "processed2.csv" using 3:($1==3072?$8:1/0) with lines linewidth 3 title '3072^2', \
    "processed2.csv" using 3:8:($1==3072?$13:1/0):xtic(3) with points pointtype 1 pointsize 0.1 palette linewidth 20 notitle, \
    "processed2.csv" using 3:($1==1536?$8:1/0) with lines linewidth 3 title '1536^2', \
    "processed2.csv" using 3:8:($1==1536?$13:1/0):xtic(3) with points pointtype 1 pointsize 0.1 palette linewidth 20 notitle