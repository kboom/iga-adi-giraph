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
capacityParameter=args[3]
loadParameter=args[4]
setwd(paste0(getwd(), "/results-charts/out"))


scalability = read_excel(sourceExcel) %>%
    filter(str_detect(experiment_type, experimentType))

# Clean up the data
matchingRoundEpt <- scalability %>% 
    group_by(roundEpt) %>%
    summarise(n = n()) %>%
    filter(n >= 7)

scalability = scalability %>%
    filter(compareToWeak != measurement) %>%
    filter(roundEpt %in% matchingRoundEpt$roundEpt) %>%
    rename(loadParameter = paste(loadParameter), capacityParameter = paste(capacityParameter))

z <- sample(1:2, 100, TRUE)
png(filename=sprintf("gustafsson-%s-%s.png", experimentType, capacityParameter), res=120, width=640, height=640)
plot(
    unique(scalability$problemSize),
    xlab = "Thread count [-]",
    ylab = "Weak speedup [-]",
    xlim = c(min(scalability$loadParameter), max(scalability$loadParameter)),
    ylim = c(1, max(scalability$capacityParameter)),
    log="xy",
    type = "n"
)
grid(nx = NULL, ny = NULL, col = "lightgray", lty = "dotted", lwd = par("lwd"), equilogs = TRUE)

color_pallete_function <- colorRampPalette(brewer.pal(3, "Dark2"))
roundEptVector = factor(scalability$roundEpt)
mycolors <- color_pallete_function(nlevels(roundEptVector))

legend(
    "topleft",
    legend = sort(unique(scalability$roundEpt)),
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

for (sizePerThread in scalability$roundEpt) {
selectedScalability <- scalability %>%
    filter(roundEpt == paste(sizePerThread)) %>%
    group_by(loadParameter) %>%
    summarize(capacityParameter = mean(capacityParameter))

model = lm(
    formula = capacityParameter ~ loadParameter,
    data = selectedScalability
)

print(summary(model))

selectedScalabilityCont = with(selectedScalability, expand.grid(loadParameter=seq(min(loadParameter), 2 * max(loadParameter))))
fit <- predict(
    object = model,
    newdata = selectedScalabilityCont,
    interval = "confidence",
    level = 0.95
)
fitDF <- as.data.frame(fit)

points(
    selectedScalability,
    pch = 16,
    col = mycolors[match(sizePerThread, roundEptVector)]
)
lines(selectedScalabilityCont$loadParameter, fitDF$fit)
}
dev.off()

