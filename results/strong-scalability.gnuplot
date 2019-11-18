set datafile separator ","
set output 'strong-scalability.png'
set term png size 1000, 1000
set bmargin 4
set tmargin 4
set lmargin 9

set style data histograms
set style histogram rowstacked
set boxwidth 0.8
set style fill solid 1.0 border -1
set view map
set grid
set autoscale fix
set xlabel 'Nodes [-]' offset -2
set ylabel 'Time [ms]' offset -2
set format y "10^{%T}"
set logscale y

set multiplot layout 2,2 columnsfirst title "{/:Bold=15 Strong scalability}"

do for [indx in "12288 6144 3072 1536"] {
	set title sprintf("Problem %s^2",indx)
	plot \
		'cluster.csv' using 2:($1==indx?$5:1/0):xtic(2) \
		with boxes t "Total", \
		'cluster.csv' using 2:($1==indx?$4:1/0):xtic(2) \
		with boxes t "Initialisation" \
}