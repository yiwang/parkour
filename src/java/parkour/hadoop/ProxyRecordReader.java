package parkour.hadoop;

import java.io.IOException;

import clojure.lang.IDeref;
import clojure.lang.RT;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class ProxyRecordReader<K, V> extends RecordReader<K, V> {
  protected IRecordReader<K, V> irr;

  public ProxyRecordReader(IRecordReader<K, V> irr) {
    this.irr = irr;
  }

  @Override
  public void close() throws IOException {
    irr.close();
  }

  @Override
  public K getCurrentKey() throws IOException, InterruptedException {
    return irr.getCurrentKey();
  }

  @Override
  public V getCurrentValue() throws IOException, InterruptedException {
    return irr.getCurrentValue();
  }

  @Override
  public float getProgress() throws IOException, InterruptedException {
    return irr.getProgress();
  }

  @Override
  public void initialize(InputSplit split, TaskAttemptContext context)
      throws IOException, InterruptedException {
    this.irr = irr.initialize(split, context);
  }

  @Override
  public boolean nextKeyValue() throws IOException, InterruptedException {
    return irr.nextKeyValue();
  }
}
