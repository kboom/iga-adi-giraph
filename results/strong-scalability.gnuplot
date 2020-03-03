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
set xlabel 'Threads [-]' offset -2
set ylabel 'Time [ms]' offset -2
set format y "10^{%T}"
set logscale y
set logscale x 2

set multiplot layout 2,2 columnsfirst

do for [indx in "12288 6144 3072 1536"] {
	set title sprintf("%s^2 elements",indx)
	plot \
		'strong-scalability.csv' using 3:($1==indx?$6:1/0):xtic(3) \
		with boxes t "Total", \
		'strong-scalability.csv' using 3:($1==indx?$5:1/0):xtic(3) \
		with boxes t "Initialisation" \
}