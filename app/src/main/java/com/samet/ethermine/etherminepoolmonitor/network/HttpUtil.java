package com.samet.ethermine.etherminepoolmonitor.network;


import android.os.AsyncTask;
import android.util.Log;

import com.samet.ethermine.etherminepoolmonitor.model.EstimatedEarnings;
import com.samet.ethermine.etherminepoolmonitor.model.MinerData;
import com.samet.ethermine.etherminepoolmonitor.model.Payout;
import com.samet.ethermine.etherminepoolmonitor.model.Round;
import com.samet.ethermine.etherminepoolmonitor.model.Settings;
import com.samet.ethermine.etherminepoolmonitor.model.Worker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by samet on 17.06.2017.
 */

public class HttpUtil extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... strings) {
        try {
            HttpURLConnection urlConnection;
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            Log.e("Ethermine Pool Monitor", "Failed to get miner data from pool", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        /*
            I have used multiple try-catch block because I wanted to continue parsing even though it catches an exception.
         */

        JSONObject data = null;
        //// TODO: 29.06.2017 http request fail ederse bisiler yapmak gerek yoksa null pointer yiyoz
        try {
            data = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            data = new JSONObject();
        }

        JSONObject minerStats = null;
        try {
            minerStats = data.getJSONObject("minerStats");
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            minerStats = new JSONObject();
        }
        try {
            MinerData.getInstance().setActiveWorkers(minerStats.getInt("activeWorkers"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setActiveWorkers(0);
        }
        try {
            MinerData.getInstance().setValidShares(minerStats.getInt("validShares"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setValidShares(0);
        }
        try {
            MinerData.getInstance().setStaleShares(minerStats.getInt("staleShares"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setStaleShares(0);
        }
        try {
            MinerData.getInstance().setInvalidShares(minerStats.getInt("invalidShares"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setInvalidShares(0);
        }

        try {
            MinerData.getInstance().setAddress(data.getString("address"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setAddress("Address not found!");
        }
        try {
            MinerData.getInstance().setHashrate(data.getString("hashRate"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setHashrate("0.0 H/s");
        }
        try {
            MinerData.getInstance().setReportedHashrate(data.getString("reportedHashRate"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setReportedHashrate("0.0 H/s");
        }
        try {
            MinerData.getInstance().setAvarageHashrate(data.getDouble("avgHashrate"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setAvarageHashrate(0.0);
        }
        try {
            MinerData.getInstance().setUnpaid(data.getDouble("unpaid"));
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            MinerData.getInstance().setUnpaid(0.0);
        }

        JSONArray workerNames = null;
        try {
            workerNames = data.getJSONObject("workers").names();
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        JSONObject workers = null;
        try {
            workers = data.getJSONObject("workers");
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        List<Worker> workerList = new ArrayList<>();
        if (workerNames != null) {
            for (int i = 0; i < workerNames.length(); i++) {
                try {
                    workerList.add(Worker.fromJsonData(workers.getJSONObject(workerNames.getString(i))));
                } catch (JSONException e) {
                    Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
                }
            }
        }
        MinerData.getInstance().setWorkers(workerList);

        JSONArray payouts = null;
        try {
            payouts = data.getJSONArray("payouts");
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        List<Payout> payoutList = new ArrayList<>();
        if (payouts != null) {
            for (int i = 0; i < payouts.length(); i++) {
                try {
                    payoutList.add(Payout.fromJsonData(payouts.getJSONObject(i)));
                } catch (JSONException e) {
                    Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
                }
            }
        }
        MinerData.getInstance().setPayouts(payoutList);

        JSONArray rounds = null;
        try {
            rounds = data.getJSONArray("rounds");
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        List<Round> roundList = new ArrayList<>();
        if (rounds != null) {
            for (int i = rounds.length() - 1; i >= 0; i--) {
                try {
                    roundList.add(Round.fromJsonData(rounds.getJSONObject(i)));
                } catch (JSONException e) {
                    Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
                }
            }
        }
        MinerData.getInstance().setRounds(roundList);

        JSONObject settings = null;
        try {
            settings = data.getJSONObject("settings");
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        MinerData.getInstance().setSettings(Settings.fromJsonData(settings));

        EstimatedEarnings estimatedEarnings = new EstimatedEarnings();
        double ethPerMin = 0, btcPerMin = 0, usdPerMin = 0;
        try {
            ethPerMin = data.getDouble("ethPerMin");
            estimatedEarnings.setEthPerMin(ethPerMin);
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        try {
            usdPerMin = data.getDouble("usdPerMin");
            estimatedEarnings.setUsdPerMin(usdPerMin);
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        try {
            btcPerMin = data.getDouble("btcPerMin");
            estimatedEarnings.setBtcPerMin(btcPerMin);
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
        MinerData.getInstance().setEstimatedEarnings(estimatedEarnings);
    }
}
