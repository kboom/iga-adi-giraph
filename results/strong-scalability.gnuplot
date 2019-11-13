set title "Serial scalability"
set datafile separator ","
set xlabel 'Problem size [P]'
set ylabel 'Nodes [N]'
set zlabel 'Time [ms]'
set grid
set term png
set view map
set pm3d lighting
set boxwidth 0.4 abs
set xyplane at 0
set view 45, 235
set pm3d border lc black
set style fill solid
set pm3d depthorder base
set grid z vertical lw 1.0
set output 'strong-scalability.png'

set linetype 24576 lc rgb '#000000'
set linetype 12288 lc rgb '#333333'
set linetype 6144 lc rgb '#555555'
set linetype 3072 lc rgb '#999999'
set linetype 1536 lc rgb '#C0C0C0'

ti(col) = sprintf("%s",col)

splot for [indx in "12288 6144 3072 1536"] \
	'cluster.csv' using 1:2:($1==indx?$5:1/0):xticlabels(1):yticlabels(2) \
	with boxes title ti(indx)