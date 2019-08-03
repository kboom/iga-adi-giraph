package edu.agh.iga.adi.giraph.direction;

public abstract class IgaMessage {

  private final long srcId;
  private final long dstId;
  private final IgaOperation

  protected IgaMessage(long srcId, long dstId) {
    this.srcId = srcId;
    this.dstId = dstId;
  }

  public long getSrcId() {
    return srcId;
  }

  public long getDstId() {
    return dstId;
  }

}
