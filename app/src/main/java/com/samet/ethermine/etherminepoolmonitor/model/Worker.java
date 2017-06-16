package com.samet.ethermine.etherminepoolmonitor.model;

/**
 * Created by samet on 16.06.2017.
 */

public class Worker {
    private String name;
    private String hashRate;
    private String reportedHashRate;
    private int validShares;
    private int staleShares;
    private int invalidShares;
    private long workerLastSubmitTime;

    public Worker(String name, String hashRate, String reportedHashRate, int validShares, int staleShares, int invalidShares, long workerLastSubmitTime) {
        this.name = name;
        this.hashRate = hashRate;
        this.reportedHashRate = reportedHashRate;
        this.validShares = validShares;
        this.staleShares = staleShares;
        this.invalidShares = invalidShares;
        this.workerLastSubmitTime = workerLastSubmitTime;
    }

    public Worker() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashRate() {
        return hashRate;
    }

    public void setHashRate(String hashRate) {
        this.hashRate = hashRate;
    }

    public String getReportedHashRate() {
        return reportedHashRate;
    }

    public void setReportedHashRate(String reportedHashRate) {
        this.reportedHashRate = reportedHashRate;
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

    public long getWorkerLastSubmitTime() {
        return workerLastSubmitTime;
    }

    public void setWorkerLastSubmitTime(long workerLastSubmitTime) {
        this.workerLastSubmitTime = workerLastSubmitTime;
    }
}
