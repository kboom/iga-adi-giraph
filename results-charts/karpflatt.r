#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(RColorBrewer)
library(stringr)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the capacity and load parameters", call.=FALSE)
}

sourceExcel=args[1]
experimentType=args[2]

setwd(paste0(getwd(), "/results-charts/out"))


data = read_excel(sourceExcel) %>%
    filter(str_detect(experiment_type, experimentType)) %>%
    filter(problem_size >= 1536) %>%
    filter(problem_size <= 12288) %>%
    group_by(threads, problem_size) %>%
    summarize(karp_flatt_metric = mean(karp_flatt_metric)) %>%
    mutate(karp_flatt_metric = ifelse(karp_flatt_metric < 0, 0, karp_flatt_metric))

problemSizes = rev(sort(unique(data$problem_size)))

png(filename=sprintf("karpflatt-%s.png", experimentType), res=120, width=640, height=640)

plot(
    c(), c(),
    log = "x",
    xlab = "Thread count [-]",
    ylab = "Overhead [%]",
    xlim = c(2, max(data$threads)),
    ylim = c(-0.01, 0.15),
    xaxt="n",
    yaxt="n",
    type = "n"
)
xtics <- unique(data$threads)
ytics <- seq(0, 0.15, 0.025)

axis(side=1, at=xtics)
axis(side=2, at=ytics, las=1, labels = ytics*100)
abline(h=ytics, v=xtics, col="lightgray", lty=3)

mycolors <- brewer.pal(length(problemSizes), "Dark2")

for (problemSize in problemSizes) {
    selectedData <- data %>%
        filter(problem_size == paste(problemSize))

    lines(
        selectedData$threads,
        selectedData$karp_flatt_metric,
        pch = 16,
        lwd=1,
        type = "o",
        col = mycolors[match(problemSize, problemSizes)]
    )
}

legend(
    "topleft",
    legend = problemSizes,
    col=mycolors,
    pch = 16,
    bty = "n", 
    pt.cex = 1.2, 
    cex = 1, 
    text.col = "black",
    box.lty = 2,
    box.lwd = 2,
    box.col = "black",
    horiz = F, 
    inset = c(0.01, 0.01)
)


dev.off()
