#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(stringr)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
experimentType=args[2]
capacityParameter=args[3]
loadParameter=args[4]
selectedProblemSize=args[5]

setwd(paste0(getwd(), "/results-charts/out"))


selectedScalability <- read_excel(sourceExcel) %>% 
    filter(str_detect(experiment_type, experimentType)) %>%
    filter(problem_size == selectedProblemSize) %>%
    select(paste(loadParameter), paste(capacityParameter)) %>%
    rename(loadParameter = paste(loadParameter), capacityParameter = paste(capacityParameter))

# SCALABILITY MODEL
usl.model <- usl(capacityParameter ~ loadParameter, data = selectedScalability)

# SMOOTH CONFIDENCE BOUNDS
loadParameters <- with(selectedScalability, expand.grid(loadParameter=seq(min(loadParameter), 4 * max(loadParameter))))
fit <- predict(usl.model, newdata = loadParameters, interval = "confidence", level = 0.99)
usl.polygon <- matrix(c(loadParameters[, 1],rev(loadParameters[, 1]), fit[,'lwr'],rev(fit[,'upr'])), nrow = 2 * nrow(loadParameters))

summary(usl.model)

mostOptimisticPeak <- max(fit[,'upr'])

# PLOT: real
png(filename=sprintf("scalability-%s-%s-%s.png", experimentType, selectedProblemSize, capacityParameter), res=120, width=640, height=640)
plot(selectedScalability, xlab = "Threads [-]", ylab = "Speedup [-]", ylim = c(1, 1.5 * mostOptimisticPeak), xlim = c(1, max(loadParameters)), type = "n", xaxt="n", yaxt="n", log = "xy")
xticks <- 2^seq(0, 10, 1);
yticks <- c(1,10,100,1000);
minorYTics <- rep(1:9, 21)*(10^rep(0:10, each=9))
axis(1L,xticks,xticks,las=2);
axis(2L,yticks,yticks);
abline(v=xticks, col="gray70", lty="dotted", lwd=par("lwd"))
abline(h=yticks, col="gray70", lty="dashed", lwd=par("lwd"))
abline(h=minorYTics, col="gray95", lty="dotted", lwd=par("lwd"))

usl.polygon[usl.polygon<0] <- 0.00000000001

polygon(usl.polygon, lty = 2, lwd = 1, border = "black", col = adjustcolor("gray", alpha.f=0.6))

points(selectedScalability, pch = 16)
lines(loadParameters[, 1], fit[,'fit'])
plot(usl.model, add = TRUE, bounds = FALSE)

legend(
    "topleft",
    legend = c("Recorded time", "Model"), 
    col = c("black", "black"), 
    pch = c(20, NA),
    lty = c(NA, 1),
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

coefficients <- coef(usl.model)
alpha <- format(coefficients[1], digits = 3, scientific = TRUE)
beta <- format(coefficients[2], digits = 3, scientific = TRUE)
gamma <- format(coefficients[3], digits = 3, scientific = TRUE)
mtext(sprintf("α = %s, β = %s, γ = %s", alpha, beta, gamma), side = 3, line = 1)
dev.off()
