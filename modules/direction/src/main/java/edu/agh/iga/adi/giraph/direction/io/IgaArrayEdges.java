package edu.agh.iga.adi.giraph.direction.io;

import com.google.common.collect.UnmodifiableIterator;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.edge.ReuseObjectsOutEdges;
import org.apache.giraph.utils.EdgeIterables;
import org.apache.hadoop.io.IntWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable.codeOfOperation;
import static edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable.operationOfCode;

public class IgaArrayEdges implements ReuseObjectsOutEdges<IntWritable, IgaOperationWritable> {

    private IntArrayList neighbors;
    private ShortArrayList edgeValues;

    @Override
    public void initialize(int capacity) {
        neighbors = new IntArrayList(capacity);
        edgeValues = new ShortArrayList(capacity);
    }

    @Override
    public void initialize(Iterable<Edge<IntWritable, IgaOperationWritable>> edges) {
        EdgeIterables.initialize(this, edges);
    }

    @Override
    public void initialize() {
        neighbors = new IntArrayList();
        edgeValues = new ShortArrayList();
    }

    @Override
    public void add(Edge<IntWritable, IgaOperationWritable> edge) {
        neighbors.add(edge.getTargetVertexId().get());
        edgeValues.add(codeOfOperation(edge.getValue().getIgaOperation()));
    }

    @Override
    public void remove(IntWritable targetVertexId) {
        throw new IllegalStateException("IGA-ADI should not remove edges");
    }

    @Override
    public int size() {
        return neighbors.size();
    }

    @Override
    public Iterator<Edge<IntWritable, IgaOperationWritable>> iterator() {
        return new UnmodifiableIterator<Edge<IntWritable, IgaOperationWritable>>() {
            /** Wrapped neighbors iterator. */
            private final IntIterator neighborsIt = neighbors.iterator();
            /** Wrapped edge values iterator. */
            private final ShortIterator edgeValuesIt = edgeValues.iterator();
            /** Representative edge object. */
            private final Edge<IntWritable, IgaOperationWritable> representativeEdge =
                    EdgeFactory.create(new IntWritable(), new IgaOperationWritable());

            @Override
            public boolean hasNext() {
                return neighborsIt.hasNext();
            }

            @Override
            public Edge<IntWritable, IgaOperationWritable> next() {
                representativeEdge.getTargetVertexId().set(neighborsIt.nextInt());
                representativeEdge.getValue().setIgaOperation(operationOfCode(edgeValuesIt.nextShort()));
                return representativeEdge;
            }
        };
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(neighbors.size());
        IntIterator neighborsIt = neighbors.iterator();
        ShortIterator edgeValuesIt = edgeValues.iterator();
        while (neighborsIt.hasNext()) {
            out.writeLong(neighborsIt.nextInt());
            out.writeDouble(edgeValuesIt.nextShort());
        }
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        int numEdges = in.readInt();
        initialize(numEdges);
        for (int i = 0; i < numEdges; ++i) {
            neighbors.add(in.readInt());
            edgeValues.add(in.readShort());
        }
    }
}
