set datafile separator ","
set output '3d.png'
set term png size 800, 800

set view map
set size ratio .9

set datafile missing
set object 1 rect from graph 0, graph 0 to graph 1, graph 1 back
set object 1 rect fc rgb "white" fillstyle solid 1.0
set logscale y
set autoscale fix

splot "cluster-p.csv" using 3:8:6:xtic(3) with points pointtype 5 pointsize 1 palette linewidth 30 title ''