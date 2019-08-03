package edu.agh.iga.adi.giraph.core;

public abstract class IgaMessage {

  private final long srcId;
  private final long dstId;
  private final IgaOperation operation;

  protected IgaMessage(long srcId, long dstId, IgaOperation operation) {
    this.srcId = srcId;
    this.dstId = dstId;
    this.operation = operation;
  }

  public long getSrcId() {
    return srcId;
  }

  public long getDstId() {
    return dstId;
  }

  public IgaOperation getOperation() {
    return operation;
  }

}
