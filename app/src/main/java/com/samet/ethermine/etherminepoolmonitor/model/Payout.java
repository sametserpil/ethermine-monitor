package com.samet.ethermine.etherminepoolmonitor.model;

import android.util.Log;

import com.samet.ethermine.etherminepoolmonitor.misc.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by samet on 17.06.2017.
 */

public class Payout {

    private int id = -1;
    private String miner = "unknown";
    private int start = 0;
    private int end = 0;
    private double amount = 0;
    private String txHash = "";
    private String paidOn = "";

    public Payout(int id, String miner, int start, int end, double amount, String txHash, String paidOn) {
        this.id = id;
        this.miner = miner;
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.txHash = txHash;
        this.paidOn = paidOn;
    }

    public static Payout fromJsonData(JSONObject jsonData) {
        Payout payout = new Payout();
        try {
            payout.setPaidOn(jsonData.getString("paidOn"));
            payout.setAmount(jsonData.getDouble("amount"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse json data", e);
        } finally {
            return payout;
        }
    }

    public Payout() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getAmount() {
        return String.format(Locale.getDefault(), "%.4f", (amount / Utils.ethDividerConst));
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getPaidOn() {
        return Utils.simplifyDateString(paidOn);
    }

    public void setPaidOn(String paidOn) {
        this.paidOn = paidOn;
    }


}
