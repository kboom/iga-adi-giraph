#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(stringr)
library(RColorBrewer)
library(ggplot2)
library(ggExtra)
library(tidyr)
library(tibble)
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



sim <- read_excel(sourceExcel) %>% 
    filter(simulation_id == selectedSimulation)

values <- sim %>%
    select(superstep_id, paste(groupParameter), paste(valueParameter)) %>%
    rename(groupParameter = paste(groupParameter), valueParameter = paste(valueParameter)) %>%
    mutate(
        superstep_id = as.numeric(superstep_id),
        groupParameter = as.numeric(groupParameter),
        valueParameter = as.numeric(valueParameter)
    )

cumsumValueBySuperstep <- values %>%
    group_by(superstep_id) %>%
    arrange(superstep_id) %>%
    summarise(totalValue = sum(valueParameter)) %>%
    mutate(totalValue = cumsum(totalValue))

cumsumValueByGroup <- values %>%
    group_by(groupParameter) %>%
    arrange(superstep_id) %>%
    mutate(total = cumsum(valueParameter))

cummulativeEffortBySuperstep <- values %>%
    inner_join(cumsumValueByGroup, by = c("groupParameter","superstep_id")) %>%
    group_by(superstep_id, groupParameter) %>%
    summarise(n = sum(total)) %>%
    mutate(percentage = n / sum(n))

transpose_map_superstep <- mean(sim$transpose_map_superstep)
transpose_reduce_superstep <- mean(sim$transpose_reduce_superstep)
init_superstep <- mean(sim$init_superstep)
first_root_superstep <- mean(sim$first_root_superstep)
second_root_superstep <- mean(sim$second_root_superstep)
first_gather <- mean(sim$first_gather)
first_scatter <- mean(sim$first_scatter)
second_gather <- mean(sim$second_gather)
second_scatter <- mean(sim$second_scatter)

workerPalette <- colorRampPalette(c(brewer.pal(11, "Paired")))(length(unique(values$groupParameter)))

png(filename=sprintf("%s-density-%s-%s.png", selectedSimulation, valueParameter, groupParameter), res=120, width=1000, height=600)
common <- ggplot(values, aes(x=superstep_id)) +
    coord_cartesian(clip="off") +
    scale_fill_manual(values = workerPalette) +
    scale_x_continuous(expand = c(0.01,0.01), breaks = unique(values$superstep_id)) +
    scale_y_continuous(expand = c(0.01,0.01)) +
    theme(
        axis.text.x = element_text(angle = 90, hjust = 1),
    ) +
    xlab("")

contribution <- common +
    geom_area(data = cummulativeEffortBySuperstep, aes(y=percentage, fill=factor(groupParameter)), alpha=0.6, size=0.6, colour="black") +
    ylab("Contribution [-]") +
    labs(fill = str_to_sentence(paste(groupParameter) %>% str_replace_all(c("_" = " ")))) +
    geom_vline(aes(xintercept=transpose_map_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=transpose_reduce_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=init_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=first_root_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=second_root_superstep), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=first_scatter), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=first_gather), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=second_scatter), color="black", linetype="dashed", size=0.25) +
    geom_vline(aes(xintercept=second_gather), color="black", linetype="dashed", size=0.25) +
    annotate("text", x = init_superstep, y = 0.5, label = "Initialization", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = transpose_map_superstep, y = 0.5, label = "Transpose map", vjust=-0.8, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = transpose_reduce_superstep, y = 0.5, label = "Transpose reduce", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = first_root_superstep, y = 0.5, label = "First root", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = second_root_superstep, y = 0.5, label = "Second root", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = first_gather, y = 0.5, label = "First gather", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = first_scatter, y = 0.5, label = "First scatter", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = second_gather, y = 0.5, label = "Second gather", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white") +
    annotate("text", x = second_scatter, y = 0.5, label = "Second scatter", vjust=1.5, angle=90, size = 3, fontface = 2, color = "white")
    
cumsumValueBySuperstep

totalTime <- common +
    geom_area(data = cumsumValueBySuperstep, aes(y=totalValue), alpha=1, size=0.6, fill="#c0c0c0", colour="black") +
    ylab(sprintf("Sum [%s]", valueUnit))

fig <- ggarrange(
    totalTime,
    contribution,
    ncol = 1,
    nrow = 2,
    heights = c(0.25, 0.75),
    align = 'v',
    legend = "right",
    common.legend = TRUE
)

annotate_figure(
    fig,
    top = "", # make space
    bottom = "Superstep [-]",
    left = str_to_sentence(paste(valueParameter) %>% str_replace_all(c("_" = " ")))
)

dev.off()
