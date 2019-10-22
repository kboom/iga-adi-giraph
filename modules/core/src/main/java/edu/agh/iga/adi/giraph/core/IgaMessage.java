package edu.agh.iga.adi.giraph.core;

public abstract class IgaMessage {

  private int srcId;
  private IgaOperation operation;

  protected IgaMessage(int srcId, IgaOperation operation) {
    this.srcId = srcId;
    this.operation = operation;
  }

  public IgaMessage reattach(int srcId) {
    this.srcId = srcId;
    return this;
  }

  public int getSrcId() {
    return srcId;
  }

  public IgaOperation getOperation() {
    return operation;
  }

}