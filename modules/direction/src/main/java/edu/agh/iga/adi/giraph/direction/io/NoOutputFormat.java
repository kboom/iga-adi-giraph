package edu.agh.iga.adi.giraph.direction.io;

import org.apache.giraph.io.formats.GiraphTextOutputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class NoOutputFormat extends GiraphTextOutputFormat {

  static final NoOutputFormat NO_OUTPUT_FORMAT = new NoOutputFormat();

  @Override
  public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext job) {
    return new RecordWriter<Text, Text>() {
      @Override
      public void write(Text text, Text text2) {
        // do nothing
      }

      @Override
      public void close(TaskAttemptContext taskAttemptContext) {
        // do nothing
      }
    };
  }

  @Override
  protected String getSubdir() {
    return "";
  }

}
