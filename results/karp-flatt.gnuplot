set datafile separator ","
set output 'karp-flatt.png'
set term png size 800, 800
set tmargin 4
set lmargin 10
set bmargin 5

set xlabel 'Threads n [-]'
set ylabel 'Serial fraction e(n,p)'

set grid
set autoscale fix
set logscale x 2

set key left top
set key box lt -1 lw 2 width 1 height 1 opaque

set style line 12288 lc rgb 'red' pt 12 ps 1 lw 2   # square
set style line 6144 lc rgb 'blue' pt 12 ps 1 lw 2  # square
set style line 3072 lc rgb 'green' pt 12 ps 1 lw 2  # square
set style line 1536 lc rgb 'black' pt 12 ps 1 lw 2  # square

plot \
    "weak-scalability.csv" using 3:($1==12288?$27:1/0):xtic($3) with points ls 12288 title '12288', \
    "weak-scalability.csv" using 3:($1==6144?$27:1/0):xtic($3) with points ls 6144 title '6144', \
    "weak-scalability.csv" using 3:($1==3072?$27:1/0):xtic($3) with points ls 3072 title '3072'