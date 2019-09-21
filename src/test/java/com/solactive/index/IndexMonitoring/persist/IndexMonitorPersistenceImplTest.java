package com.solactive.index.IndexMonitoring.persist;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexMonitorPersistenceImplTest
{

  @Test
  public void givenTrickTimestampLessThanOr60SecondWhenTrickCalledThenTrueReturned()
  {
    IndexMonitorPersistenceImpl indexMonitorPersistenceImpl = new IndexMonitorPersistenceImpl();
    boolean trick = indexMonitorPersistenceImpl.trick(new Tricks("instrument1", 8.5, System.currentTimeMillis()));
    assertTrue(trick);
    indexMonitorPersistenceImpl.clearTrick();
  }

  @Test
  public void givenTrickTimestampMoreThan60SecondWhenTrickCalledThenFalseReturned()
  {
    IndexMonitorPersistenceImpl indexMonitorPersistenceImpl = new IndexMonitorPersistenceImpl();
    boolean trick = indexMonitorPersistenceImpl.trick(new Tricks("instrument2", 8.5, 1569048425));
    assertFalse(trick);
    indexMonitorPersistenceImpl.clearTrick();
  }

  @Test
  public void getInstrumentStatistics()
  {
    IndexMonitorPersistenceImpl indexMonitorPersistenceImpl = new IndexMonitorPersistenceImpl();
    indexMonitorPersistenceImpl.trick(new Tricks("instrument1", 8.5, System.currentTimeMillis()));
    indexMonitorPersistenceImpl.trick(new Tricks("instrument2", 7.5, System.currentTimeMillis()));
    indexMonitorPersistenceImpl.trick(new Tricks("instrument3", 6.5, 1569048425));
    indexMonitorPersistenceImpl.trick(new Tricks("instrument4", 4.5, 1569048426));
    List<Tricks> instrumentStatistics = indexMonitorPersistenceImpl.getInstrumentStatistics();
    assertEquals(2, instrumentStatistics.size());
  }
}