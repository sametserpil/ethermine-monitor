package com.samet.ethermine.etherminepoolmonitor.model;

import android.util.Log;

import com.samet.ethermine.etherminepoolmonitor.misc.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samet on 21.06.2017.
 */

public class Round {
    private int block = -1;
    private double amount = 0;

    public static Round fromJsonData(JSONObject jsonData) {
        Round round = new Round();
        try {
            round.setBlock(Integer.parseInt(jsonData.getString("block")));
            round.setAmount(jsonData.getDouble("amount"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse json data", e);
        } finally {
            return round;
        }
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public double getAmount() {
        return (amount / Utils.ethDividerConst);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
