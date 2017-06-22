package com.samet.ethermine.etherminepoolmonitor.model;

/**
 * Created by samet on 22.06.2017.
 */

public class Settings {
    private String email;
    private String minPayout;
    private String ip;

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
}
