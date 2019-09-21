package com.solactive.index.IndexMonitoring.manager;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import com.solactive.index.IndexMonitoring.persist.IndexMonitorPersistence;
import com.solactive.index.IndexMonitoring.response.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IndexMonitorManagerImplTest
{

  @Mock
  private IndexMonitorPersistence indexMonitorPersistence;

  @InjectMocks
  private IndexMonitorManagerImpl indexMonitorManager;

  @Test
  public void givenTricksNotExistWhenTrickCalledThenFalse()
  {
    when(indexMonitorPersistence.trick(any(Tricks.class))).thenReturn(false);
    boolean doestNotExist = indexMonitorManager.trick(new Tricks());
    assertFalse(doestNotExist);
  }

  @Test
  public void givenTricksExistWhenTrickCalledThenTrue()
  {
    when(indexMonitorPersistence.trick(any(Tricks.class))).thenReturn(true);
    boolean exist = indexMonitorManager.trick(new Tricks());
    assertTrue(exist);
  }

  @Test
  public void whenNoInstrumentExistThenAllValuesReturnZero()
  {
    when(indexMonitorPersistence.getInstrumentStatistics()).thenReturn(new ArrayList<>());
    Result allInstrumentStatistics = indexMonitorManager.getAllInstrumentStatistics();
    assertEquals(0.0, allInstrumentStatistics.getAvg(), 0.0);
    assertEquals(0.0, allInstrumentStatistics.getMax(), 0.0);
    assertEquals(0.0, allInstrumentStatistics.getMin(), 0.0);
    assertEquals(0.0, allInstrumentStatistics.getCount(), 0.0);
  }

  @Test
  public void whenInstrumentExistThenStatisticsReturned()
  {
    when(indexMonitorPersistence.getInstrumentStatistics()).thenReturn(getTricks());
    Result result = indexMonitorManager.getAllInstrumentStatistics();
    assertEquals(9.59, result.getAvg(), 0.01);
    assertEquals(29.5, result.getMax(), 0.0);
    assertEquals(0.5, result.getMin(), 0.0);
    assertEquals(21, result.getCount());
  }

  @Test
  public void getInstrumentStatistics()
  {
    when(indexMonitorPersistence.getInstrumentStatistics()).thenReturn(getTricks());
    Result result = indexMonitorManager.getInstrumentStatistics("instrument_id1");
    assertEquals(10.5, result.getAvg(), 0.01);
    assertEquals(25.5, result.getMax(), 0.0);
    assertEquals(2.5, result.getMin(), 0.0);
    assertEquals(4, result.getCount());
  }

  private List<Tricks> getTricks()
  {
    return Arrays.asList(new Tricks("instrument_id1", 5.5, 1569048425),
            new Tricks("instrument_id2", 6.5, 1569060000),
            new Tricks("instrument_id3", 7.5, 1569060000),
            new Tricks("instrument_id4", 8.5, 1569060000),
            new Tricks("instrument_id5", 1.5, 1569060000),
            new Tricks("instrument_id6", 4.5, 1569060000),
            new Tricks("instrument_id7", 6.5, 1569060000),
            new Tricks("instrument_id8", 7.5, 1569060000),
            new Tricks("instrument_id7", 17.5, 1569060000),
            new Tricks("instrument_id10", 0.5, 1569049540),
            new Tricks("instrument_id11", 1.5, 1569049540),
            new Tricks("instrument_id12", 2.5, 1569049540),
            new Tricks("instrument_id13", 4.5, 1569049540),
            new Tricks("instrument_id14", 17.5, 1569060000),
            new Tricks("instrument_id15", 16.5, 1569048425),
            new Tricks("instrument_id16", 15.5, 1569060000),
            new Tricks("instrument_id17", 29.5, 1569060000),
            new Tricks("instrument_id18", 11.5, 1569048425),
            new Tricks("instrument_id1", 8.5, 1569048425),
            new Tricks("instrument_id1", 25.5, 1569048425),
            new Tricks("instrument_id1", 2.5, 1569048425));
  }
}