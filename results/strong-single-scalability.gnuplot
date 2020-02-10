set datafile separator ","
set output 'strong-single-scalability.png'
set term png size 800, 800
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

set style line 12288 \
    linecolor rgb '#003f5c' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 6144 \
    linecolor rgb '#7a5195' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 3072 \
    linecolor rgb '#ef5675' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 768 \
    linecolor rgb '#ffa600' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

set style line 384 \
    linecolor rgb '#000600' \
    linetype 1 linewidth 2 \
    pointtype 7 pointsize 1.5

plot for [indx in "12288 6144 3072 768 384"] \
	'single.csv' using ($2):($1==indx?$5:1/0):xtic($2) \
	with linespoints linestyle indx title indx