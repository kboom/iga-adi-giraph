package edu.agh.iga.adi.giraph;

import org.apache.giraph.master.DefaultMasterCompute;

public class IgaComputation extends DefaultMasterCompute {

  @Override
  public final void compute () {
    long superstep = getSuperstep(); if (superstep == 0) {
      setComputation(SendFriendsList.class); } else if (superstep == 1) {
      setComputation(JaccardComputation.class); }
  }

}
