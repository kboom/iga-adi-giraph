set datafile separator ","
set output 'weak-scalability.png'
set term png size 800, 800
set tmargin 4
set lmargin 10
set bmargin 5

set grid
set xlabel 'Threads [-]'
set ylabel 'Weak speedup [-]'
set xrange [1:256]
set yrange [1:256]
set key right bottom

set style line 1 lc rgb 'red' pt 5 ps 2   # square
set style line 2 lc rgb 'blue' pt 7 ps 2  # circle

o1(x) = a1*x
fit o1(x) 'weak-scalability.csv' using 3:($8==590000?$22:1/0) via a1
o1title = sprintf("O(N) = %.2fN (+/- %.2f\%)", a1, a1_err/a1*100)

o2(x) = a2*x
fit o2(x) 'weak-scalability.csv' using 3:($8==147000?$22:1/0) via a2
o2title = sprintf("O(N) = %.2fN (+/- %.2f\%)", a2, a2_err/a2*100)

f(x) = x

plot \
    "weak-scalability.csv" using 3:($8==590000?$22:1/0):xtic($3) with points ls 1 title '590k E/T', \
    o1(x) with lines lc rgb 'red' lw 2 title o1title, \
    "weak-scalability.csv" using 3:($8==147000?$22:1/0):xtic($3) with points ls 2 title '147k E/T', \
    o2(x) with lines lc rgb 'blue' lw 2 title o2title, \
    f(x) with lines lc rgb 'green' lw 2 title 'Ideal speedup'