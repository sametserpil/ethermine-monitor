package com.samet.ethermine.etherminepoolmonitor.model;

import java.util.Locale;

/**
 * Created by samet on 29.06.2017.
 */

public class EstimatedEarnings {

    private double ethPerMin;
    private double btcPerMin;
    private double usdPerMin;

    public double getEthPerMin() {
        return ethPerMin;
    }

    public void setEthPerMin(double ethPerMin) {
        this.ethPerMin = ethPerMin;
    }

    public double getBtcPerMin() {
        return btcPerMin;
    }

    public void setBtcPerMin(double btcPerMin) {
        this.btcPerMin = btcPerMin;
    }

    public double getUsdPerMin() {
        return usdPerMin;
    }

    public void setUsdPerMin(double usdPerMin) {
        this.usdPerMin = usdPerMin;
    }

    public String getFormattedEthPerMin() {
        return String.format(Locale.getDefault(), "%.8f", ethPerMin);
    }

    public String getFormattedEthPerHour() {
        return String.format(Locale.getDefault(), "%.8f", ethPerMin * 60);
    }

    public String getFormattedEthPerDay() {
        return String.format(Locale.getDefault(), "%.8f", ethPerMin * 60 * 24);
    }

    public String getFormattedEthPerWeek() {
        return String.format(Locale.getDefault(), "%.8f", ethPerMin * 60 * 24 * 7);
    }

    public String getFormattedEthPerMonth() {
        return String.format(Locale.getDefault(), "%.8f", ethPerMin * 60 * 24 * 30);
    }

    public String getFormattedBtcPerMin() {
        return String.format(Locale.getDefault(), "%.8f", btcPerMin);
    }

    public String getFormattedBtcPerHour() {
        return String.format(Locale.getDefault(), "%.8f", btcPerMin * 60);
    }

    public String getFormattedBtcPerDay() {
        return String.format(Locale.getDefault(), "%.8f", btcPerMin * 60 * 24);
    }

    public String getFormattedBtcPerWeek() {
        return String.format(Locale.getDefault(), "%.8f", btcPerMin * 60 * 24 * 7);
    }

    public String getFormattedBtcPerMonth() {
        return String.format(Locale.getDefault(), "%.8f", btcPerMin * 60 * 24 * 30);
    }

    public String getFormattedUsdPerMin() {
        return String.format(Locale.getDefault(), "%.8f", usdPerMin);
    }

    public String getFormattedUsdPerHour() {
        return String.format(Locale.getDefault(), "%.8f", usdPerMin * 60);
    }

    public String getFormattedUsdPerDay() {
        return String.format(Locale.getDefault(), "%.8f", usdPerMin * 60 * 24);
    }

    public String getFormattedUsdPerWeek() {
        return String.format(Locale.getDefault(), "%.8f", usdPerMin * 60 * 24 * 7);
    }

    public String getFormattedUsdPerMonth() {
        return String.format(Locale.getDefault(), "%.8f", usdPerMin * 60 * 24 * 30);
    }
}
