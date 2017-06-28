package com.samet.ethermine.etherminepoolmonitor.model;

import com.samet.ethermine.etherminepoolmonitor.misc.Utils;

import java.util.List;
import java.util.Locale;

/**
 * Created by samet on 19.06.2017.
 */

public class MinerData {

    private static MinerData instance = new MinerData();

    private MinerData() {
    }

    public static MinerData getInstance() {
        return instance;
    }

    private int activeWorkers;

    public int getActiveWorkers() {
        return activeWorkers;
    }

    public void setActiveWorkers(int activeWorkers) {
        this.activeWorkers = activeWorkers;
    }

    private String address;
    private String hashrate;
    private String reportedHashrate;
    private double avarageHashrate;
    private List<Payout> payouts;
    private List<Worker> workers;
    private List<Round> rounds;
    private Settings settings;
    private int validShares;
    private int staleShares;
    private int invalidShares;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    private double unpaid;

    public String getFormattedUnpaid() {
        return String.format(Locale.getDefault(), "%.4f ETH", (unpaid / Utils.ethDividerConst));
    }

    public double getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(double unpaid) {
        this.unpaid = unpaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHashrate() {
        return hashrate;
    }

    public void setHashrate(String hashrate) {
        this.hashrate = hashrate;
    }

    public String getReportedHashrate() {
        return reportedHashrate;
    }

    public void setReportedHashrate(String reportedHashrate) {
        this.reportedHashrate = reportedHashrate;
    }

    public double getAvarageHashrate() {
        return avarageHashrate;
    }

    public String getFormattedAvarageHashrate() {
        if (hashrate.contains("MH") || reportedHashrate.contains("MH")) {
            return String.format(Locale.getDefault(), "%.1f MH/s", avarageHashrate / 1e+6);
        } else if (hashrate.contains("GH") || reportedHashrate.contains("MH")) {
            return String.format(Locale.getDefault(), "%.1f GH/s", avarageHashrate / 1e+9);
        } else if (hashrate.contains("KH") || reportedHashrate.contains("MH")) {
            return String.format(Locale.getDefault(), "%.1f KH/s", avarageHashrate / 1e+3);
        } else {
            return String.format(Locale.getDefault(), "%.1f H/s", avarageHashrate);
        }
    }

    public void setAvarageHashrate(double avarageHashrate) {
        this.avarageHashrate = avarageHashrate;
    }

    public List<Payout> getPayouts() {
        return payouts;
    }

    public void setPayouts(List<Payout> payouts) {
        this.payouts = payouts;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public int getValidShares() {
        return validShares;
    }

    public void setValidShares(int validShares) {
        this.validShares = validShares;
    }

    public int getStaleShares() {
        return staleShares;
    }

    public void setStaleShares(int staleShares) {
        this.staleShares = staleShares;
    }

    public int getInvalidShares() {
        return invalidShares;
    }

    public void setInvalidShares(int invalidShares) {
        this.invalidShares = invalidShares;
    }
}
