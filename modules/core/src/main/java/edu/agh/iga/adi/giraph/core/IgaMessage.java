package edu.agh.iga.adi.giraph.core;

public abstract class IgaMessage {

  private long srcId;
  private IgaOperation operation;

  protected IgaMessage(long srcId, IgaOperation operation) {
    this.srcId = srcId;
    this.operation = operation;
  }

  public IgaMessage reattach(long srcId) {
    this.srcId = srcId;
    return this;
  }

  public long getSrcId() {
    return srcId;
  }

  public IgaOperation getOperation() {
    return operation;
  }

}