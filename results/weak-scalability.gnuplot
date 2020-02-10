set datafile separator ","
set output 'weak-scalability.png'
set term png size 800, 800
set tmargin 4
set lmargin 10
set bmargin 5

set view map
set grid
set autoscale fix
set xlabel 'Nodes [-]' offset -2
set ylabel 'Time [ms]' offset -2
set logscale x

otitle(indx) = sprintf("%s elements / thread", indx)

set style line 1 \
    linecolor rgb '#003f5c' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

o1(x) = a1*x + c1
fit o1(x) 'weak.csv' using 1:9 via a1, c1


plot 'weak.csv' using 1:9:xtic(1) \
	with linespoints linestyle 1 title otitle("9M")