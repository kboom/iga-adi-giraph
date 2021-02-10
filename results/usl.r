#!/usr/bin/env Rscript
library(usl)
library(readxl)
library(dplyr)
library(stringr)
library(RColorBrewer)
library(ComplexHeatmap)
library(tidyr)
library(tibble)
library(vegetarian)

args = commandArgs(trailingOnly=TRUE)
if (length(args)==0) {
  stop("Need to specify the source excel and the problem size", call.=FALSE)
}

sourceExcel=args[1]
selectedSimulation=args[2]

setwd("results/out")


standardize <- function(z) {
  rowmed <- apply(z, 1, median)
  rowmad <- apply(z, 1, mad)  # median absolute deviation
  rv <- sweep(z, 1, rowmed,"-")  #subtracting median expression
  rv <- sweep(z, 1, rowmad,"/")  #subtracting median expression
  return(rv)
}

values <- read_excel(sourceExcel) %>%
  filter(simulation_id == selectedSimulation) %>%
  select(superstep_id, worker_id, superstep_time) %>%
  mutate(
    superstep_id = as.numeric(superstep_id),
    worker_id = as.numeric(worker_id),
    superstep_time = as.numeric(superstep_time)
  ) %>%
  arrange(worker_id) %>%
  pivot_wider(names_from = worker_id, values_from = superstep_time) %>%
  column_to_rownames("superstep_id") %>%
  standardize

values

col <- colorRampPalette(brewer.pal(10, "RdYlBu"))(256)


png(filename=sprintf("heatmap-%s.png", selectedSimulation), res=120, width=1000, height=1000)

Heatmap(
  values,
  name = "mtcars", #title of legend
  column_title = "Variables",
  row_title = "Samples",
  row_names_gp = gpar(fontsize = 7) # Text size for row names
)
dev.off()
