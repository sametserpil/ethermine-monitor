package com.samet.ethermine.etherminepoolmonitor.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samet on 22.06.2017.
 */

public class Settings {
    private String email = "";
    private String minPayout = "";
    private String ip = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMinPayout() {
        return minPayout;
    }

    public void setMinPayout(String minPayout) {
        this.minPayout = minPayout;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static Settings fromJsonData(JSONObject jsonData) {
        Settings settings = new Settings();
        try {
            settings.setEmail(jsonData.getString("email"));
            settings.setIp(jsonData.getString("ip"));
            settings.setMinPayout(jsonData.getString("minPayout"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse json data", e);
        } finally {
            return settings;
        }
    }
}
