set title "Serial scalability"
set datafile separator ","
set xlabel 'Problem Size [N]'
set ylabel 'Time [ms]'
set grid
set term png
set view map
set output 'serial-scalability.png'
set autoscale fix
set logscale
set format y "10^%L"
set fit errorvariables

o(x) = a*x + c

fit o(x) 'single.csv' using ($1**2):($2==1?$5:1/0) via a, c
otitle = sprintf("O(N) = %.1f*10^{-2}N+%d (+/- %.2f\%)", a*100, c, a_err/a*100)

plot \
	'single.csv' using ($1**2):($2==1?$5:1/0):xtic($1) pt 5 ps 1 lc rgb 'black' notitle, \
	 o(x) with lines lc rgb 'black' title otitle

replot