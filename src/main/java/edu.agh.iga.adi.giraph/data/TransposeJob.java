package edu.agh.iga.adi.giraph.data;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Job that transposes a dense matrix stored in HDFS.
 */
public final class TransposeJob {

  public static class Map extends Mapper<LongWritable, Text, LongWritable, MapWritable> {
    private Text word = new Text();

    public void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
      String[] s = value.toString().split("\\t");
      int row = Integer.parseInt(s[0].trim());
      String[] vals = s[1].trim().split("\\s+");
      MapWritable map = new MapWritable();
      int col = 0;
      for (String v : vals) {
        int val = Integer.parseInt(v);
        map.put(new LongWritable(row), new IntWritable(val));
        context.write(new LongWritable(col), map);
        col++;
      }
    }
  }

  public static class Reduce extends Reducer<LongWritable, MapWritable, LongWritable, Text> {

    public void reduce(LongWritable key, Iterable<MapWritable> maps, Context context)
        throws IOException, InterruptedException {
      SortedMap<LongWritable, IntWritable> rowVals = new TreeMap<>();
      for (MapWritable map : maps) {
        for (java.util.Map.Entry<Writable, Writable> entry : map.entrySet()) {
          rowVals.put((LongWritable) entry.getKey(), (IntWritable) entry.getValue());
        }
      }

      StringBuffer sb = new StringBuffer();
      for (IntWritable rowVal : rowVals.values()) {
        sb.append(rowVal.toString());
        sb.append(" ");
      }
      context.write(key, new Text(sb.toString()));
    }
  }

  public static void transpose(Path input, Path output) {
    Job job = createJob();
    job.setJarByClass(TransposeJob.class);
    job.setOutputKeyClass(LongWritable.class);
    job.setOutputValueClass(MapWritable.class);
    job.setMapperClass(Map.class);
    job.setReducerClass(Reduce.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    try {
      FileInputFormat.addInputPath(job, input);
    } catch (IOException e) {
      throw new IllegalStateException("Could not add input path", e);
    }
    FileOutputFormat.setOutputPath(job, output);

    execute(job);
  }

  private static Job createJob() {
    try {
      return new Job(new Configuration(), "transpose");
    } catch (IOException e) {
      throw new IllegalStateException("Could not create hadoop job", e);
    }
  }

  private static void execute(Job job) {
    try {
      job.waitForCompletion(true);
    } catch (Exception e) {
      throw new IllegalStateException("Could not transpose matrix", e);
    }
  }

}
