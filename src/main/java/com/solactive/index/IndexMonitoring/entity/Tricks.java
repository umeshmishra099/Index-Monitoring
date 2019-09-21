package com.solactive.index.IndexMonitoring.entity;

public class Tricks
{
  private String instrument;
  private double price;
  private long timestamp;

  public Tricks(String instrument, double price, long timestamp)
  {
    this.instrument = instrument;
    this.price = price;
    this.timestamp = timestamp;
  }

  public Tricks()
  {
  }

  public String getInstrument()
  {
    return instrument;
  }

  public void setInstrument(String instrument)
  {
    this.instrument = instrument;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public long getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(long timestamp)
  {
    this.timestamp = timestamp;
  }

  @Override
  public String toString()
  {
    return "Tricks{" +
            "instrument='" + instrument + '\'' +
            ", price=" + price +
            ", timestamp=" + timestamp +
            '}';
  }
}
