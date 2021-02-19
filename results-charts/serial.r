#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(stringr)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the capacity and load parameters", call.=FALSE)
}

sourceExcel=args[1]
capacityParameter=args[2]
loadParameter=args[3]
setwd(paste0(getwd(), "/results-charts/out"))


scalability = read_excel(sourceExcel) %>%
    filter(threads == 1) %>%
    select(paste(loadParameter), paste(capacityParameter)) %>%
    rename(loadParameter = paste(loadParameter), capacityParameter = paste(capacityParameter)) %>%
    mutate(loadParameter = loadParameter^2)

    scalability

model = lm(
    formula = capacityParameter ~ loadParameter,
    data = scalability
)

print(summary(model))


png(filename=sprintf("serial-%s-%s.png", capacityParameter, loadParameter), res=120, width=640, height=640)

plot(
    unique(scalability$capacityParameter),
    xlab = sprintf("%s [-]", str_to_sentence(paste(loadParameter) %>% str_replace_all(c("_" = " ")))),
    ylab = str_to_sentence(paste(capacityParameter) %>% str_replace_all(c("_" = " ")) %>% str_replace_all(c("ms" = "[ms]"))),
    xlim = c(min(scalability$loadParameter), max(scalability$loadParameter)),
    ylim = c(0, max(scalability$capacityParameter)),
    type = "n"
)
grid(nx = NULL, ny = NULL, col = "lightgray", lty = "dotted", lwd = par("lwd"), equilogs = TRUE)

scalabilityCont = with(scalability, expand.grid(loadParameter=seq(min(loadParameter), 2 * max(loadParameter), length=1000)))
fit <- predict(
    object = model,
    newdata = scalabilityCont,
    interval = "confidence",
    level = 0.99
)
fitDF <- as.data.frame(fit)
polygon.x <- c(scalabilityCont$loadParameter, rev(scalabilityCont$loadParameter))
polygon.y <- c(fitDF$lwr, rev(fitDF$upr))
polygon(x=polygon.x, y=polygon.y, col=adjustcolor("black", alpha.f=0.1), border=NA)

points(
    scalability,
    pch = 16,
    col = "black"
)
lines(scalabilityCont$loadParameter, fitDF$fit)

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


dev.off()

