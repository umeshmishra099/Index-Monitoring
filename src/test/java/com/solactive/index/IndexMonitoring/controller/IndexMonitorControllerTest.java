package com.solactive.index.IndexMonitoring.controller;

import com.solactive.index.IndexMonitoring.entity.Tricks;
import com.solactive.index.IndexMonitoring.manager.IndexMonitorManager;
import com.solactive.index.IndexMonitoring.response.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(IndexMonitorController.class)
public class IndexMonitorControllerTest
{

  @Autowired
  private MockMvc mvc;

  @MockBean
  private IndexMonitorManager indexMonitorManager;

  @Test
  public void trickNotOlderThanSixtySecond() throws Exception
  {
    when(indexMonitorManager.trick(any(Tricks.class))).thenReturn(true);
    MockHttpServletResponse mockHttpServletResponse = mvc.perform(post("/ticks")
            .content("{\"instrument\": \"IBM.N\", \"price\": 143.82, \"timestamp\": 1478192204000}").contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn().getResponse();
    assertEquals(201, mockHttpServletResponse.getStatus());
    assertEquals("", mockHttpServletResponse.getContentAsString());
  }

  @Test
  public void trickOlderThanSixtySecond() throws Exception
  {
    when(indexMonitorManager.trick(any(Tricks.class))).thenReturn(false);
    MockHttpServletResponse mockHttpServletResponse = mvc.perform(post("/ticks")
            .content("{\"instrument\": \"IBM.N\", \"price\": 143.82, \"timestamp\": 1478192204000}").contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn().getResponse();
    assertEquals(204, mockHttpServletResponse.getStatus());
    assertEquals("", mockHttpServletResponse.getContentAsString());
  }

  @Test
  public void getAllInstrumentStatistics() throws Exception
  {
    when(indexMonitorManager.getAllInstrumentStatistics()).thenReturn(new Result(8.5, 19.8, 3.5, 20L));
    MockHttpServletResponse mockHttpServletResponse = mvc.perform(get("/statistics")).andReturn().getResponse();
    assertEquals(200, mockHttpServletResponse.getStatus());
    assertEquals("{\"avg\":8.5,\"max\":19.8,\"min\":3.5,\"count\":20}", mockHttpServletResponse.getContentAsString());
  }

  @Test
  public void getInstrumentStatistics() throws Exception
  {
    when(indexMonitorManager.getInstrumentStatistics("instrument_identifier")).thenReturn(new Result(4.5, 10.8, 3.5, 10L));
    MockHttpServletResponse mockHttpServletResponse = mvc.perform(get("/statistics/instrument_identifier")).andReturn().getResponse();
    assertEquals(200, mockHttpServletResponse.getStatus());
    assertEquals("{\"avg\":4.5,\"max\":10.8,\"min\":3.5,\"count\":10}", mockHttpServletResponse.getContentAsString());
  }
}