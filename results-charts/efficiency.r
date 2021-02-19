#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(RColorBrewer)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
capacityParameter=args[2]
loadParameter=args[3]
selectedProblemSize=args[4]

setwd(paste0(getwd(), "/results-charts/out"))


selectedScalability <- read_excel(sourceExcel) %>% 
    filter(problemSize == selectedProblemSize) %>%
    select(paste(loadParameter), paste(capacityParameter)) %>%
    rename(loadParameter = paste(loadParameter), capacityParameter = paste(capacityParameter)) %>%
    group_by(loadParameter) %>%
    summarise(
        capacityParameter = mean(capacityParameter)
    )

usl.model <- usl(capacityParameter ~ loadParameter, data = selectedScalability)
efficiencyVector <- efficiency(usl.model)

efficiencyVector

png(filename=sprintf("efficiency-%s-%s.png", selectedProblemSize, capacityParameter), res=120, width=640, height=640)
barplot(
    height=efficiencyVector,
    ylab="Efficiency [-]",
    col="gray",
    xlab = "Threads [-]",
    las=2
)
dev.off()
