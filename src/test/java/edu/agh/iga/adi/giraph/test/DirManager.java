package edu.agh.iga.adi.giraph.test;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.fs.Path;

import static org.apache.giraph.conf.GiraphConstants.*;
import static org.apache.giraph.utils.FileUtils.deletePath;

final class DirManager {

  private static final Path DEFAULT_TEMP_DIR = new Path(System.getProperty("java.io.tmpdir"), "giraph");

  private final GiraphConfiguration config;

  private DirManager(GiraphConfiguration config) {
    this.config = config;
  }

  static void setDefaultDirs(GiraphConfiguration config) {
    config.set(ZOOKEEPER_DIR, getTempPath("zk"));
    ZOOKEEPER_MANAGER_DIRECTORY.set(config, getTempPath("zkm"));
    CHECKPOINT_DIRECTORY.set(config, getTempPath("checkpoints"));
  }

  static DirManager standardDirManager(GiraphConfiguration config) {
    return new DirManager(config);
  }

  void recreateDirectories() {
    Path zookeeperDir = new Path(config.get(ZOOKEEPER_DIR));
    Path zkManagerDir = new Path(ZOOKEEPER_MANAGER_DIRECTORY.get(config));
    Path checkPointDir = new Path(CHECKPOINT_DIRECTORY.get(config));

    deleteDir(config, zookeeperDir);
    deleteDir(config, zkManagerDir);
    deleteDir(config, checkPointDir);
  }

  private static String getTempPath(String name) {
    return new Path(DEFAULT_TEMP_DIR, name).toString();
  }

  private void deleteDir(GiraphConfiguration conf, Path dir) {
    try {
      deletePath(conf, dir);
    } catch(Exception e) {
      throw new IllegalStateException("Could not create directory for tests", e);
    }
  }

}
