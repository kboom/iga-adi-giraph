package edu.agh.iga.adi.giraph.direction.io.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.operations.*;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BACKWARDS_SUBSTITUTE_BRANCH_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BACKWARDS_SUBSTITUTE_INTERIM_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BACKWARDS_SUBSTITUTE_ROOT_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MERGE_AND_ELIMINATE_BRANCH_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MERGE_AND_ELIMINATE_INTERIM_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MERGE_AND_ELIMINATE_ROOT_OPERATION;

public final class IgaOperationWritable implements Writable {

  private static final BiMap<Class<?>, Short> OPERATION_MAPPING = ImmutableBiMap.<Class<?>, Short>builder()
      .put(BackwardsSubstituteBranchOperation.class, (short) 1)
      .put(BackwardsSubstituteInterimOperation.class, (short) 2)
      .put(BackwardsSubstituteRootOperation.class, (short) 3)
      .put(MergeAndEliminateBranchOperation.class, (short) 4)
      .put(MergeAndEliminateInterimOperation.class, (short) 5)
      .put(MergeAndEliminateLeavesOperation.class, (short) 6)
      .put(MergeAndEliminateRootOperation.class, (short) 7)
      .build();

  private static final BiMap<Class<?>, IgaOperation> OPERATION_CACHE = ImmutableBiMap.<Class<?>, IgaOperation>builder()
          .put(BackwardsSubstituteBranchOperation.class, BACKWARDS_SUBSTITUTE_BRANCH_OPERATION)
          .put(BackwardsSubstituteInterimOperation.class, BACKWARDS_SUBSTITUTE_INTERIM_OPERATION)
          .put(BackwardsSubstituteRootOperation.class, BACKWARDS_SUBSTITUTE_ROOT_OPERATION)
          .put(MergeAndEliminateBranchOperation.class, MERGE_AND_ELIMINATE_BRANCH_OPERATION)
          .put(MergeAndEliminateInterimOperation.class, MERGE_AND_ELIMINATE_INTERIM_OPERATION)
          .put(MergeAndEliminateLeavesOperation.class, MERGE_AND_ELIMINATE_LEAVES_OPERATION)
          .put(MergeAndEliminateRootOperation.class, MERGE_AND_ELIMINATE_ROOT_OPERATION)
          .build();

  private IgaOperation igaOperation;

  public IgaOperationWritable(IgaOperation igaOperation) {
    this.igaOperation = igaOperation;
  }

  @Override
  public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeShort(codeOfOperation(igaOperation));
  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {
    igaOperation = operationOfCode(dataInput.readShort());
  }

  public IgaOperation getIgaOperation() {
    return igaOperation;
  }

  public static short codeOfOperation(IgaOperation operation) {
    return OPERATION_MAPPING.get(operation.getClass());
  }

  public void setIgaOperation(IgaOperation igaOperation) {
    this.igaOperation = igaOperation;
  }

  public static IgaOperation operationOfCode(short code) {
    try {
      return OPERATION_CACHE.get(OPERATION_MAPPING.inverse().get(code));
    } catch (Exception e) {
      throw new IllegalStateException("Could not find operation for code " + code);
    }
  }

  @SuppressWarnings("unused")
  public IgaOperationWritable() {

  }

}
