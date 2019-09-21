package com.solactive.index.IndexMonitoring.persist;

import com.solactive.index.IndexMonitoring.entity.Tricks;

import java.util.List;

public interface IndexMonitorPersistence
{
  boolean trick(Tricks tricks);

  List<Tricks> getInstrumentStatistics();
}
