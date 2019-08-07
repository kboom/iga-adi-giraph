package edu.agh.iga.adi.giraph.core;

public abstract class IgaMessage {

  private final long srcId;
  private final IgaOperation operation;

  protected IgaMessage(long srcId, IgaOperation operation) {
    this.srcId = srcId;
    this.operation = operation;
  }

  public long getSrcId() {
    return srcId;
  }

  public IgaOperation getOperation() {
    return operation;
  }

}