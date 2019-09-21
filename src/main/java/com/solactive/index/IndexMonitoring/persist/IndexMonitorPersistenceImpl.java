package com.solactive.index.IndexMonitoring.persist;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class IndexMonitorPersistenceImpl implements IndexMonitorPersistence
{
  private static final Logger logger = LoggerFactory.getLogger(IndexMonitorPersistenceImpl.class);

  private List<Tricks> allTricks = Collections.synchronizedList(new ArrayList<>());

  @Override
  public boolean trick(Tricks tricks)
  {
    logger.debug("Trick " + tricks);
    synchronized (this)
    {
      long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - tricks.getTimestamp());
      boolean oldTrick = seconds <= 60;
      if (oldTrick)
      {
        allTricks.add(tricks);
      }
      return oldTrick;
    }
  }

  @Override
  public List<Tricks> getInstrumentStatistics()
  {
    long currentTimeMillis = System.currentTimeMillis();
    return allTricks.stream()
            .filter(tricks -> TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis - tricks.getTimestamp()) <= 60)
            .collect(Collectors.toList());
  }

  protected void clearTrick()
  {
    allTricks.clear();
  }
}
