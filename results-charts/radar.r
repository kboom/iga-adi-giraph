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
library(fmsb)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
selectedSimulation=args[2]
valueParameter=args[3]
groupParameter=args[4]

setwd(paste0(getwd(), "/results-charts/out"))

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

worker_count <- max(sim$worker_id)

data <- sim %>%
    select(superstep_id, paste(groupParameter), paste(valueParameter)) %>%
    rename(groupParameter = paste(groupParameter), valueParameter = paste(valueParameter)) %>%
    mutate(
        superstep_id = as.numeric(superstep_id),
        groupParameter = as.numeric(groupParameter),
        valueParameter = as.numeric(valueParameter)
    ) %>%
    group_by(superstep_id, groupParameter) %>%
    summarise(v = sum(valueParameter)) %>%
    mutate(percentage = v / sum(v)) %>%
    select(-v) %>%
    pivot_wider(names_from = groupParameter, values_from = percentage) %>%
    as_tibble %>%
    filter(superstep_id %in% c(
        init_superstep,
        first_root_superstep,
        transpose_map_superstep,
        transpose_reduce_superstep,second_root_superstep,
        first_gather,
        first_scatter,
        second_gather,
        second_scatter
    ))

png(filename=sprintf("%s-radar-%s-%s.png", selectedSimulation, valueParameter, groupParameter), res=120, width=1000, height=600)

par(mar=c(1, 1, 1, 1))
layout(matrix(1:9, ncol=3))

dataForSuperstep <- function(input, ss) {
    filtered <- data %>% filter(superstep_id == ss) %>% select(-superstep_id)
    return (rbind(rep(max(filtered),worker_count), rep(0,worker_count), filtered))
}

supersteps = tibble(
    "Initialization" = init_superstep,
    "First gather" = first_gather,
    "First root" = first_root_superstep,
    "First scatter" = first_scatter,
    "Transpose map" = transpose_map_superstep,
    "Transpose reduce" = transpose_reduce_superstep,
    "Second gather" = second_gather,
    "Second root" = second_root_superstep,
    "Second scatter" = second_scatter
) %>% pivot_longer(everything(), names_to = "name", values_to = "superstep_id")

lapply(1:nrow(data), function(i) {
    superstep <- supersteps[i,]
    radarchart(
        dataForSuperstep(data, superstep$superstep_id)
    )
    title(main=superstep$name)
})

dev.off()
