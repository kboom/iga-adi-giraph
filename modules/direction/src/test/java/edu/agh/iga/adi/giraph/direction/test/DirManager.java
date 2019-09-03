package edu.agh.iga.adi.giraph.direction.test;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.fs.Path;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.COEFFICIENTS_OUTPUT;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.ZK_DIR;
import static java.lang.System.getProperty;
import static org.apache.giraph.conf.GiraphConstants.*;
import static org.apache.giraph.utils.FileUtils.deletePath;

public final class DirManager {

  private final GiraphConfiguration config;

  private DirManager(GiraphConfiguration config) {
    this.config = config;
  }

  static DirManager standardDirManager(GiraphConfiguration config) {
    return aDirManager(config).build();
  }

  public void recreateDirectories() {
    Path zookeeperDir = new Path(config.get(ZOOKEEPER_DIR));
    Path zkManagerDir = new Path(ZOOKEEPER_MANAGER_DIRECTORY.get(config));
    Path checkPointDir = new Path(CHECKPOINT_DIRECTORY.get(config));
    Path coefficientsOutputDir = new Path(COEFFICIENTS_OUTPUT.get(config));

    deleteDir(config, zookeeperDir);
    deleteDir(config, zkManagerDir);
    deleteDir(config, checkPointDir);
    deleteDir(config, coefficientsOutputDir);
  }

  private void deleteDir(GiraphConfiguration conf, Path dir) {
    try {
      deletePath(conf, dir);
    } catch (Exception e) {
      throw new IllegalStateException("Could not create directory for tests", e);
    }
  }

  public static DirManagerBuilder aDirManager(GiraphConfiguration config) {
    return new DirManagerBuilder(config);
  }

  public static class DirManagerBuilder {

    private static final Path DEFAULT_TEMP_DIR = new Path(getProperty("java.io.tmpdir"), "giraph");

    private static String getTempPath(String name) {
      return new Path(DEFAULT_TEMP_DIR, name).toString();
    }

    private final GiraphConfiguration config;

    private String zookeeperDir = getTempPath("zk");
    private String zkManagerDir = getTempPath("zkm");
    private String checkPointDir = getTempPath("checkpoints");

    private DirManagerBuilder(GiraphConfiguration config) {
      this.config = config;
    }

    public DirManager build() {
      setDirsIfNotSet();
      return new DirManager(config);
    }

    private void setDirsIfNotSet() {
      CHECKPOINT_DIRECTORY.setIfUnset(config, checkPointDir);
      ZOOKEEPER_MANAGER_DIRECTORY.setIfUnset(config, zkManagerDir);
      ZK_DIR.setIfUnset(config, zookeeperDir);
    }

  }

}
