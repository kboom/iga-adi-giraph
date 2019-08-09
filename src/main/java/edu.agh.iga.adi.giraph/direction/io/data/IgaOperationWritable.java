package edu.agh.iga.adi.giraph.direction.io.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.operations.*;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class IgaOperationWritable implements Writable {

  private static final BiMap<Class<?>, Integer> OPERATION_MAPPING = ImmutableBiMap.<Class<?>, Integer>builder()
      .put(BackwardsSubstituteBranchOperation.class, 1)
      .put(BackwardsSubstituteInterimOperation.class, 2)
      .put(BackwardsSubstituteRootOperation.class, 3)
      .put(MergeAndEliminateBranchOperation.class, 4)
      .put(MergeAndEliminateInterimOperation.class, 5)
      .put(MergeAndEliminateLeavesOperation.class, 6)
      .put(MergeAndEliminateRootOperation.class, 7)
      .build();

  private IgaOperation igaOperation;

  public IgaOperationWritable(IgaOperation igaOperation) {
    this.igaOperation = igaOperation;
  }

  @Override
  public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeInt(codeOfOperation(igaOperation));
  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {
    igaOperation = operationOfCode(dataInput.readInt());
  }

  public IgaOperation getIgaOperation() {
    return igaOperation;
  }

  private static int codeOfOperation(IgaOperation operation) {
    return OPERATION_MAPPING.get(operation.getClass());
  }

  private static IgaOperation operationOfCode(int code) {
    try {
      return (IgaOperation) OPERATION_MAPPING.inverse().get(code).newInstance();
    } catch (Exception e) {
      throw new IllegalStateException("Could not find operation for code " + code);
    }
  }

  @SuppressWarnings("unused")
  public IgaOperationWritable() {

  }

}
