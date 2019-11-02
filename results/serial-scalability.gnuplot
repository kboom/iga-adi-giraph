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

f(x) = 1

plot 'single.csv' using ($1**2):($2==1?$5:0):xtic($1) title ''

replot