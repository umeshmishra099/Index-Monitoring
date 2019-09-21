package com.solactive.index.IndexMonitoring.response;

public class Result
{
  private double avg;
  private double max;
  private double min;
  private long count;

  public Result(double avg, double max, double min, long count)
  {
    this.avg = avg;
    this.max = max;
    this.min = min;
    this.count = count;
  }

  public double getAvg()
  {
    return avg;
  }

  public double getMax()
  {
    return max;
  }

  public double getMin()
  {
    return min;
  }

  public long getCount()
  {
    return count;
  }
}
