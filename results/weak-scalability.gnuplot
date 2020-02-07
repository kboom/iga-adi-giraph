set title "Weak scalability"
set datafile separator ","
set output 'weak-scalability.png'
set term png size 1000, 1000
set tmargin 4
set lmargin 10
set bmargin 5

set view map
set grid
set autoscale fix
set xlabel 'Nodes [-]' offset -2
set ylabel 'Time [ms]' offset -2
set format y "10^{%T}"
set logscale y

otitle(indx) = sprintf("%s^2 elements", indx)

set style line 24576 \
    linecolor rgb '#003f5c' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 12288 \
    linecolor rgb '#7a5195' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 6144 \
    linecolor rgb '#ef5675' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 3072 \
    linecolor rgb '#ffa600' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

o1(x) = a1*x + c1
fit o1(x) 'cluster.csv' using 2:($1==24576?$5:1/0) via a1, c1

o2(x) = a2*x + c2
fit o2(x) 'cluster.csv' using 2:($1==12288?$5:1/0) via a2, c2

o3(x) = a3*x + c3
fit o3(x) 'cluster.csv' using 2:($1==6144?$5:1/0) via a3, c3

o4(x) = a4*x + c4
fit o4(x) 'cluster.csv' using 2:($1==3072?$5:1/0) via a4, c4


plot for [indx in "24576 12288 6144 3072"] \
	'cluster.csv' using ($2):($1==indx?$5:1/0):xtic($2) \
	with linespoints linestyle indx title otitle(indx), \
	o1(x) title "linear fit - 24566^2" with lines linestyle 24576, \
	o2(x) title "linear fit - 12288^2" with lines linestyle 12288, \
	o3(x) title "linear fit - 6144^2" with lines linestyle 6144, \
	o4(x) title "linear fit - 3072^2" with lines linestyle 3072