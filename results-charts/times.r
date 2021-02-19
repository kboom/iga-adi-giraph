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

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
experimentType=args[2]

setwd(paste0(getwd(), "/results-charts/out"))


png(filename=sprintf("times-%s.png", experimentType), res=120, width=1000, height=1000)
values <- read_excel(sourceExcel) %>%
    filter(str_detect(experiment_type, experimentType)) %>%
    as_tibble
    
totals <- values %>%
    group_by(problem_size) %>%
    summarise(
        step_solution_ms_max = max(step_solution_ms)
    )

myplot <- function(problemSize) {
    values <- values %>% filter(problem_size == problemSize)

    maxTime <- filter(totals, problem_size == problemSize)
    maxTime <- maxTime$step_solution_ms_max

    totals <- values %>%
        group_by(threads) %>%
        summarize(step_solution_ratio = mean(step_solution_ms / maxTime))

    ratios <- values %>%
        group_by(problem_size, threads) %>%
        summarise(
            initialisation_ms = mean(init_ms / step_solution_ms),
            factorization_ms = mean(factorization_ms / step_solution_ms),
            backwards_substitution_ms = mean(backwards_substitution_ms / step_solution_ms),
            transpose_map_ms = mean(transpose_map_ms / step_solution_ms),
            transpose_reduce_ms = mean(transpose_reduce_ms / step_solution_ms)
        ) %>%
        pivot_longer(cols = ends_with("ms"), names_to = "type", values_to = "time") %>%
        as_tibble %>%
        mutate(type = str_to_sentence(type) %>% str_replace_all(c("_" = " ")) %>% str_replace_all(c("ms" = "")))

    m <- ggplot(values, aes(x=threads)) +
        geom_bar(data = ratios, mapping = aes(y=time, fill=type), width = 0.7, stat="identity") +
        geom_point(data = totals, mapping = aes(y=step_solution_ratio), stat="identity") +
        scale_x_continuous(expand = c(0.02,0.02), breaks=unique(values$threads), trans = "log2") +
        scale_y_continuous(
            expand = c(0.01,0.01),
            sec.axis = sec_axis(~. * maxTime, name = "")
        ) +
        scale_fill_brewer(palette="Set1") +
        xlab("") +
        ylab("") +
        labs(fill="Components") +
        theme(legend.position="top") +
        guides(fill=guide_legend(nrow=2,byrow=TRUE))

    return(m)
}

f <- ggarrange(
    myplot(12288),
    myplot(6144),
    myplot(3072),
    myplot(1536),
    labels = c("12288x12288", "6144x6144", "3072x3072", "1536x1536"),
    font.label = list(size = 12, face = "plain"),
    label.y = 1.06,
    ncol = 2,
    nrow = 2,
    align = 'v',
    legend = "bottom",
    common.legend = TRUE
)

annotate_figure(f, top = "", left = "Contribution [-]", bottom = "Threads [-]", right = "Total time [ms]")

dev.off()
