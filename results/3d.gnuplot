set datafile separator ","
set output '3d.png'
set term png size 800, 800
set xlabel 'Threads [-]' offset -2
set ylabel 'Elements / Thread [-]'

set view map
set size ratio 1

set datafile missing
set object 1 rect from graph 0, graph 0 to graph 1, graph 1 back
set object 1 rect fc rgb "white" fillstyle solid 1.0
set logscale y
set logscale x 2
set autoscale fix


splot "processed.csv" using 3:8:13:xtic(3) with points pointtype 5 pointsize 1 palette linewidth 30 title ''