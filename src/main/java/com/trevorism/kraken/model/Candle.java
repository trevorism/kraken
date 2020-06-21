package com.trevorism.kraken.model;

import java.time.Duration;
import java.util.Date;

public class Candle {

    private Duration duration;
    private Date time;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volumeWeightedAveragePrice;
    private Double volume;
    private Long count;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolumeWeightedAveragePrice() {
        return volumeWeightedAveragePrice;
    }

    public void setVolumeWeightedAveragePrice(Double volumeWeightedAveragePrice) {
        this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Candle{" +
                "duration=" + duration +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volumeWeightedAveragePrice=" + volumeWeightedAveragePrice +
                ", volume=" + volume +
                ", count=" + count +
                '}';
    }
}
