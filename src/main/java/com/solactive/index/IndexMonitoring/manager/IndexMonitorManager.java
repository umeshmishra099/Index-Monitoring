package com.solactive.index.IndexMonitoring.manager;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import com.solactive.index.IndexMonitoring.response.Result;

public interface IndexMonitorManager
{
  boolean trick(Tricks tricks);

  Result getAllInstrumentStatistics();

  Result getInstrumentStatistics(String instrument_identifier);
}
