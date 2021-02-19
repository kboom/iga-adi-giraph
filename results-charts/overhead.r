#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(RColorBrewer)
library(ggplot2)
library(tidyr)
library(stringr)
library(ggrepel)
library(ggpubr)
library(scales)
library(tibble)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
experimentType=args[2]
capacityParameter=args[3]
loadParameter=args[4]

setwd(paste0(getwd(), "/results-charts/out"))


data <- read_excel(sourceExcel)

reverselog_trans <- function(base = exp(2)) {
    trans <- function(x) abs(log(1/x, base))
    inv <- function(x) base^(1-x)
    trans_new(paste0("reverselog-", format(base)), trans, inv, log_breaks(base = base))
}

myplot <- function(problemSize) {
    scalability = data %>%
        filter(str_detect(experiment_type, experimentType)) %>%
        filter(problem_size == problemSize) %>%
        select(paste(loadParameter), paste(capacityParameter)) %>%
        rename(loadParameter = paste(loadParameter), capacityParameter = paste(capacityParameter)) %>%
        group_by(loadParameter) %>%
        summarise(
            capacityParameter = mean(capacityParameter)
        )

    usl.model <- usl(capacityParameter ~ loadParameter, data = scalability)

    maxThreadExponent <- log2(max(scalability$loadParameter))+2
    loadParameterValues <- with(scalability, expand.grid(loadParameter=2^(0:maxThreadExponent)))

    ovhd <- overhead(usl.model, newdata = loadParameterValues) %>%
        as_tibble %>%
        rownames_to_column(var = "threads") %>%
        pivot_longer(cols = c("ideal", "coherency", "contention"), names_to = "type", values_to = "time") %>%
        mutate(threads = 2^(as.numeric(threads)-1))
        
    palette <- brewer.pal(ncol(ovhd), "Blues")

    print(summary(ovhd %>% filter(type == "coherency")))

    m <- ggplot(ovhd, aes(x=threads, y=time, fill=type)) +
        geom_bar(position="fill", width = 0.7, stat="identity") +
        scale_x_continuous(expand = c(0.02,0.02), breaks=unique(ovhd$threads), trans = 'log2') +
        scale_y_continuous(expand = c(0.01,0.01), limits=c(0,1)) +
        scale_fill_brewer(palette="Set1") +
        xlab("") +
        ylab("") +
        theme(
            axis.text.x = element_text(angle = 90, hjust = 1),
            plot.margin = unit(c(1,0.5,0,0.5), "lines")
        ) +
        labs(fill="")

    return(m)
}

png(filename=sprintf("overhead-%s-%s.png", experimentType, capacityParameter), res=120, width=1000, height=1000)

f <- ggarrange(
    myplot(12288),
    myplot(6144),
    myplot(3072),
    myplot(1536),
    labels = c("12288²", "6144²", "3072²", "1536²"),
    font.label = list(size = 12, face = "plain"),
    label.y = 1.04,
    ncol = 2,
    nrow = 2,
    align = 'hv',
    legend = "bottom",
    common.legend = TRUE
)

annotate_figure(f, fig.lab.size=5, top = "", left = "Ratio [-]", bottom = "Threads [-]")

dev.off()


# barplot(
#     height=ovhd,
#     log="y",
#     ylim=c(0.0001,1),
#     names.arg = loadParameterValues[, 1],
#     col=palette,
#     xlab = "Threads",
#     legend.text = TRUE,
#     las=2,
#     offset=0.000000000000000001 # this is better than NA as it will display all values
# )
# legend(
#     "bottomleft",
#     col=palette,
#     legend = rownames(ovhd),
#     bg="white",
#     pch = 15,
#     pt.cex = 1.2,
#     cex = 1,
#     inset=c(0.075,0.05)
# )
