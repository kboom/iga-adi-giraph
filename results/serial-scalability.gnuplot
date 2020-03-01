set datafile separator ","
set xlabel 'Problem Size [N]' offset -2
set ylabel 'Time [ms]' offset -2
set grid
set term png
set view map
set output 'serial-scalability.png'
set autoscale fix
set logscale
set format y "10^%L"
set fit errorvariables
set term png size 1000, 600
set tmargin 4
set lmargin 10
set bmargin 5


o(x) = a*x + c

fit o(x) 'strong-scalability.csv' using ($1**2):($3==1?$6:1/0) via a, c
otitle = sprintf("O(N) = %.3f*10^{-6}N (+/- %.2f\%)", a*100000, a_err/a*100)

plot \
	'strong-scalability.csv' using ($1**2):($3==1?$6:1/0):xtic($1**2) pt 7 ps 1.5 lc rgb '#ef5675' title "Data points", \
	 o(x) with lines lc rgb '#ef5675' title otitle

replot