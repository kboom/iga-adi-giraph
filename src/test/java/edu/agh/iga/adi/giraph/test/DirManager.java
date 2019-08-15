package edu.agh.iga.adi.giraph.test;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.fs.Path;

import static edu.agh.iga.adi.giraph.IgaConfiguration.COEFFICIENTS_OUTPUT;
import static org.apache.giraph.conf.GiraphConstants.*;
import static org.apache.giraph.utils.FileUtils.deletePath;

final class DirManager {

  private static final Path DEFAULT_TEMP_DIR = new Path(System.getProperty("java.io.tmpdir"), "giraph");

  private static final String DEFAULT_OUTPUT_PATH = getTempPath("output");
  private static final String DEFAULT_CHECKPOINTS_PATH = getTempPath("checkpoints");
  private static final String DEFAULT_ZKM_PATH = getTempPath("zkm");
  private static final String DEFAULT_ZK_PATH = getTempPath("zk");

  private final GiraphConfiguration config;

  private DirManager(GiraphConfiguration config) {
    this.config = config;
  }

  static void setDefaultDirs(GiraphConfiguration config) {
    config.set(ZOOKEEPER_DIR, DEFAULT_ZK_PATH);
    ZOOKEEPER_MANAGER_DIRECTORY.set(config, DEFAULT_ZKM_PATH);
    CHECKPOINT_DIRECTORY.set(config, DEFAULT_CHECKPOINTS_PATH);
    COEFFICIENTS_OUTPUT.set(config, DEFAULT_OUTPUT_PATH);
  }

  static DirManager standardDirManager(GiraphConfiguration config) {
    return new DirManager(config);
  }

  void recreateDirectories() {
    Path zookeeperDir = new Path(config.get(ZOOKEEPER_DIR));
    Path zkManagerDir = new Path(ZOOKEEPER_MANAGER_DIRECTORY.get(config));
    Path checkPointDir = new Path(CHECKPOINT_DIRECTORY.get(config));
    Path coefficientsOutputDir = new Path(COEFFICIENTS_OUTPUT.get(config));

    deleteDir(config, zookeeperDir);
    deleteDir(config, zkManagerDir);
    deleteDir(config, checkPointDir);
    deleteDir(config, coefficientsOutputDir);
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
