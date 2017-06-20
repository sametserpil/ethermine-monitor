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

    private String address;
    private String hashrate;
    private String reportedHashrate;
    private double avarageHashrate;
    private List<Payout> payouts;
    private List<Worker> workers;
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
        if (hashrate.contains("MH")) {
            return String.format(Locale.getDefault(), "%.1f MH/s", avarageHashrate / 1e+6);
        } else if (hashrate.contains("GH")) {
            return String.format(Locale.getDefault(), "%.1f GH/s", avarageHashrate / 1e+9);
        } else if (hashrate.contains("KH")) {
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
        int shares = 0;
        for (Worker worker : workers) {
            shares += worker.getValidShares();
        }
        return shares;
    }

    public int getStaleShares() {
        int shares = 0;
        for (Worker worker : workers) {
            shares += worker.getStaleShares();
        }
        return shares;
    }

    public int getInvalidShares() {
        int shares = 0;
        for (Worker worker : workers) {
            shares += worker.getInvalidShares();
        }
        return shares;
    }
}
