#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(stringr)
library(lattice)
library(latticeExtra)
library(tidyr)
library(tibble)
library(ggplot2)
library(ggExtra)
library(patchwork)
library(ggrepel)
library(ggpubr)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
selectedSimulation=args[2]
valueParameter=args[3]
groupParameter=args[4]
valueUnit=args[5]

setwd(paste0(getwd(), "/results-charts/out"))



standardize <- function(z) {
    return(t(apply(z, 1, function(x) {
        return(100* x/sum(x) - 100/length(x))
    })))
}

sim <- read_excel(sourceExcel) %>%
    filter(simulation_id == selectedSimulation)

transpose_map_superstep <- mean(sim$transpose_map_superstep)
transpose_reduce_superstep <- mean(sim$transpose_reduce_superstep)
init_superstep <- mean(sim$init_superstep)
first_root_superstep <- mean(sim$first_root_superstep)
second_root_superstep <- mean(sim$second_root_superstep)
first_gather <- mean(sim$first_gather)
first_scatter <- mean(sim$first_scatter)
second_gather <- mean(sim$second_gather)
second_scatter <- mean(sim$second_scatter)
worker_count <- max(sim$nr_węzła)

values <- sim %>%
    select(superstep_id, paste(groupParameter), paste(valueParameter)) %>%
    rename(groupParameter = paste(groupParameter), valueParameter = paste(valueParameter)) %>%
    mutate(
        superstep_id = as.numeric(superstep_id),
        groupParameter = as.numeric(groupParameter),
        valueParameter = as.numeric(valueParameter)
    )

contributionByThreadBySuperstep <- values %>%
    group_by(superstep_id, groupParameter) %>%
    summarise(v = sum(valueParameter)) %>%
    mutate(percentage = 100*v/sum(v) - 100/n())

cumsumValueBySuperstep <- values %>%
    group_by(superstep_id) %>%
    arrange(superstep_id) %>%
    summarise(totalValue = sum(valueParameter)) %>%
    mutate(totalValue = cumsum(totalValue))

png(filename=sprintf("%s-heatmap-%s-%s.png", selectedSimulation, valueParameter, groupParameter), res=120, width=1000, height=600)

common <- ggplot(values, aes(x = superstep_id)) +
    coord_cartesian(clip="off") +
    scale_x_continuous(expand = c(0.01,0.01), breaks = unique(values$superstep_id)) +
    theme(
        axis.text.x = element_text(angle = 90, hjust = 1),
        plot.margin = unit(c(0,0,0,0), "cm")
    ) +
    xlab("")

contribution <- common +
    geom_tile(data = contributionByThreadBySuperstep, aes(y = groupParameter, fill = percentage), colour = "grey50") +
    scale_y_continuous(expand = c(0.01,0.01), breaks = unique(values$groupParameter)) +
    ylab(str_wrap(sprintf("%s [-]", str_to_sentence(paste(groupParameter) %>% str_replace_all(c("_" = " ")))), 10)) +
    labs(fill = str_wrap(sprintf("Odchylenie %s [%%]", paste(valueParameter) %>% str_replace_all(c("_" = " "))), 10)) +
    scale_fill_gradient2(
        low = "blue",
        mid = "white",
        high = "red", 
        space = "Lab",
        na.value = "grey50",
        midpoint = 0,
        n.breaks = 10,
        limits = c(-100,100),
        guide = "colourbar",
        aesthetics = "fill"
    ) +
    geom_vline(aes(xintercept=transpose_map_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=transpose_reduce_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=init_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=first_root_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=second_root_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=first_scatter), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=first_gather), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=second_scatter), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=second_gather), color="black", linetype="dashed", size=0.25) +
    annotate("text", x = init_superstep, y = (worker_count+1) / 2, label = "Inicjalizacja", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = transpose_map_superstep, y = (worker_count+1) / 2, label = "Transpozycja - mapowanie", vjust=-0.8, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = transpose_reduce_superstep, y = (worker_count+1) / 2, label = "Transpozycja - redukcja", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = first_root_superstep, y = (worker_count+1) / 2, label = "Pierwszy korzeń", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = second_root_superstep, y = (worker_count+1) / 2, label = "Drugi korzeń", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = first_gather, y = (worker_count+1) / 2, label = "Pierwsze zebranie", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = first_scatter, y = (worker_count+1) / 2, label = "Pierwszy rozrzut", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = second_gather, y = (worker_count+1) / 2, label = "Drugie zebranie", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black") +
    annotate("text", x = second_scatter, y = (worker_count+1) / 2, label = "Drugi rozrzut", vjust=1.5, angle=90, size = 3, fontface = 2, color = "black")
    
cummulative <- common +
    geom_area(data = cumsumValueBySuperstep, aes(y=totalValue), alpha=1, size=0.6, fill="#c0c0c0", colour="black") +
    scale_y_continuous(expand = c(0.01,0.01)) +
    ylab(str_wrap(sprintf("%s [%s]", str_to_sentence(paste(valueParameter) %>% str_replace_all(c("_" = " "))), valueUnit), width = 5))

fig <- ggarrange(
    cummulative,
    contribution,
    ncol = 1,
    nrow = 2,
    heights = c(0.2, 0.8),
    align = 'v',
    legend = "right",
    common.legend = TRUE
)

annotate_figure(
    fig,
    top = "", # make space
    bottom = "Iteracja [-]"
)

dev.off()
