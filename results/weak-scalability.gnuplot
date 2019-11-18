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
set xlabel 'Problem size [E]' offset -2
set ylabel 'Time [ms]' offset -2
set format y "10^{%T}"
set logscale

otitle(indx) = sprintf("%s nodes (%d threads)", indx, indx * 8)

plot for [indx in "10 9 8 7 6 5 4 3 2 1"] \
	'cluster.csv' using ($1**2):($2==indx?$5:1/0):xtic($1**2) \
	with lines t otitle(indx)