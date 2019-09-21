package com.solactive.index.IndexMonitoring.controller;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import com.solactive.index.IndexMonitoring.manager.IndexMonitorManager;
import com.solactive.index.IndexMonitoring.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexMonitorController
{
  private IndexMonitorManager indexMonitorManager;

  @Autowired
  public IndexMonitorController(IndexMonitorManager indexMonitorManager)
  {
    this.indexMonitorManager = indexMonitorManager;
  }

  @RequestMapping(value = "/ticks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity trick(@RequestBody Tricks tricks)
  {
    return new ResponseEntity<>(null, indexMonitorManager.trick(tricks) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/statistics", method = RequestMethod.GET)
  public Result getAllInstrumentStatistics()
  {
    return indexMonitorManager.getAllInstrumentStatistics();
  }

  @RequestMapping(value = "/statistics/{instrument_identifier}", method = RequestMethod.GET)
  public Result getInstrumentStatistics(@PathVariable String instrument_identifier)
  {
    return indexMonitorManager.getInstrumentStatistics(instrument_identifier);
  }
}
