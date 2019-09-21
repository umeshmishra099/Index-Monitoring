package com.solactive.index.IndexMonitoring.manager;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import com.solactive.index.IndexMonitoring.persist.IndexMonitorPersistence;
import com.solactive.index.IndexMonitoring.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IndexMonitorManagerImpl implements IndexMonitorManager
{

  private static final Logger logger = LoggerFactory.getLogger(IndexMonitorManagerImpl.class);

  private IndexMonitorPersistence indexMonitorPersistence;

  @Autowired
  public IndexMonitorManagerImpl(IndexMonitorPersistence indexMonitorPersistence)
  {
    this.indexMonitorPersistence = indexMonitorPersistence;
  }

  @Override
  public boolean trick(Tricks tricks)
  {
    logger.debug("trick " + tricks);
    return indexMonitorPersistence.trick(tricks);
  }

  @Override
  public Result getAllInstrumentStatistics()
  {
    List<Tricks> tricks = indexMonitorPersistence.getInstrumentStatistics();
    return getResult(tricks);
  }

  @Override
  public Result getInstrumentStatistics(String instrument_identifier)
  {
    List<Tricks> tricks = indexMonitorPersistence.getInstrumentStatistics()
            .stream()
            .filter(trick -> trick.getInstrument().equals(instrument_identifier)).collect(Collectors.toList());
    return getResult(tricks);

  }

  private Result getResult(List<Tricks> tricks)
  {
    if (tricks.size() > 0)
    {
      List<Double> prices = tricks.stream()
              .map(Tricks::getPrice)
              .collect(Collectors.toList());

      DoubleSummaryStatistics summaryStatistics = prices
              .stream()
              .mapToDouble(price -> price)
              .summaryStatistics();

      return new Result(summaryStatistics.getAverage(),
              summaryStatistics.getMax(),
              summaryStatistics.getMin(),
              summaryStatistics.getCount());
    }
    else
    {
      return new Result(0.0, 0.0, 0.0, 0L);
    }
  }

}
