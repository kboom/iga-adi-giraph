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

fit o(x) 'single.csv' using ($1**2):($2==1?$5:1/0) via a, c
otitle = sprintf("O(N) = %.1f*10^{-2}N+%d (+/- %.2f\%)", a*100, c, a_err/a*100)

plot \
	'single.csv' using ($1**2):($2==1?$5:1/0):xtic($1) pt 7 ps 1.5 lc rgb '#ef5675' notitle, \
	 o(x) with lines lc rgb '#ef5675' title otitle

replot