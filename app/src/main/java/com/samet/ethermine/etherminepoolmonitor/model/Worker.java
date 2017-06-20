package com.samet.ethermine.etherminepoolmonitor.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samet on 16.06.2017.
 */

public class Worker {
    private String name = "";
    private String hashRate = "";
    private String reportedHashRate = "";
    private int validShares = 0;
    private int staleShares = 0;
    private int invalidShares = 0;
    private long workerLastSubmitTime = 0L;

    public Worker(String name, String hashRate, String reportedHashRate, int validShares, int staleShares, int invalidShares, long workerLastSubmitTime) {
        this.name = name;
        this.hashRate = hashRate;
        this.reportedHashRate = reportedHashRate;
        this.validShares = validShares;
        this.staleShares = staleShares;
        this.invalidShares = invalidShares;
        this.workerLastSubmitTime = workerLastSubmitTime;
    }

    public static Worker fromJsonData(JSONObject jsonData) {
        Worker worker = new Worker();
        try {
            worker.setName(jsonData.getString("worker"));
            worker.setHashRate(jsonData.getString("hashrate"));
            worker.setReportedHashRate(jsonData.getString("reportedHashRate"));
            worker.setValidShares(jsonData.getInt("validShares"));
            worker.setStaleShares(jsonData.getInt("staleShares"));
            worker.setInvalidShares(jsonData.getInt("invalidShares"));
            worker.setWorkerLastSubmitTime(jsonData.getLong("workerLastSubmitTime"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse json data", e);
        } finally {
            return worker;
        }
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
