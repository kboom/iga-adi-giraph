#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
  stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
capacityParameter=args[2]
loadParameter=args[3]
selectedProblemSize=args[4]

setwd("results/out")

scalability = read_excel(sourceExcel)
selectedScalability = scalability[scalability$problemSize == selectedProblemSize,]
selectedScalability <- selectedScalability %>%
  select(paste(loadParameter), paste(capacityParameter)) %>%
  rename(loadParameter = paste(loadParameter), capacityParameter = paste(capacityParameter))

# SCALABILITY MODEL
usl.model <- usl(capacityParameter ~ loadParameter, data = selectedScalability, gamma = 0)

# SMOOTH CONFIDENCE BOUNDS
loadParameters <- with(selectedScalability, expand.grid(loadParameter=seq(min(loadParameter), 2.5 * max(loadParameter))))
fit <- predict(usl.model, newdata = loadParameters, interval = "confidence", level = 0.99)
usl.polygon <- matrix(c(loadParameters[, 1],rev(loadParameters[, 1]), fit[,'lwr'],rev(fit[,'upr'])), nrow = 2 * nrow(loadParameters))

summary(usl.model)

mostOptimisticPeak <- max(fit[,'upr'])

# PLOT: real
png(filename=sprintf("scalability%s.png", selectedProblemSize), res=120, width=640, height=640)
plot(selectedScalability, xlab = "Threads N", ylab = expression('Speedup S=T'[N]/'T'[1]), ylim = c(0, 1.5 * mostOptimisticPeak), xlim = c(0, max(loadParameters)), type = "n")
polygon(usl.polygon, border = NA, col = "gray")
points(selectedScalability, pch = 16)
lines(loadParameters[, 1], fit[,'fit'])
plot(usl.model, add = TRUE, bounds = TRUE)

legend(
  "bottomright",
  legend = c("Data points", "Calculated scalability"),
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
