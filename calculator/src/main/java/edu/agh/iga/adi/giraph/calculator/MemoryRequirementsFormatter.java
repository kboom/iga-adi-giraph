package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class MemoryRequirementsFormatter {

  private static final String LINES = " ================================= ";
  private static final String NEWLINE = System.lineSeparator();

  static String format(MemoryRequirements requirements) {
    StringBuilder sb = new StringBuilder();
    fillHeader(sb, requirements);
    sb.append(NEWLINE);
    fillTotalMemory(requirements, sb);
    sb.append(NEWLINE);
    fillWorkerMemory(requirements, sb);
    sb.append(NEWLINE);
    return sb.toString();
  }

  private static void fillTotalMemory(MemoryRequirements memoryRequirements, StringBuilder sb) {
    sb.append("Total memory: ");
    fillMemory(sb, memoryRequirements.getTotalMemory());
  }

  private static void fillWorkerMemory(MemoryRequirements memoryRequirements, StringBuilder sb) {
    sb.append("Worker memory: ");
    fillMemory(sb, memoryRequirements.getWorkerMemory());
  }

  private static void fillMemory(StringBuilder sb, Memory workerMemory) {
    sb.append(workerMemory.inMegabytes());
    sb.append("MB");
    sb.append(" / ");
    sb.append(workerMemory.inGigabytes());
    sb.append("GB");
  }

  private static void fillHeader(StringBuilder sb, MemoryRequirements memoryRequirements) {
    sb.append(LINES);
    sb.append(memoryRequirements.getSize());
    sb.append("/");
    sb.append(memoryRequirements.getWorkers());
    sb.append(LINES);
  }

}
