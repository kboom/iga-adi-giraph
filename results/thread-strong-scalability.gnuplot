set title "Thread strong scalability"
set datafile separator ","
set xlabel 'Threads [T]'
set ylabel 'Time [ms]'
set grid
set term png
set view map
set output 'thread-strong-scalability.png'
set autoscale fix
set logscale
set format y "10^%L"
set xtics 2

set linetype 12288 lc rgb '#000000'
set linetype 6144 lc rgb '#555555'
set linetype 3072 lc rgb '#999999'
set linetype 1536 lc rgb '#C0C0C0'

plot for [indx in "12288 6144 3072 1536"] \
	'single.csv' using ($1==indx ? $2 : 1/0):5 \
	pt 5 ps 1 lc indx \
	title sprintf("%s^2", indx);

a=system("awk -F "," '{ if($1 == 12288) print $2 $5}' single.csv")

