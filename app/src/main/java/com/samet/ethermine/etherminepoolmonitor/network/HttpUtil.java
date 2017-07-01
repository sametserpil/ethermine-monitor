package com.samet.ethermine.etherminepoolmonitor.network;


import android.os.AsyncTask;
import android.util.Log;

import com.samet.ethermine.etherminepoolmonitor.misc.IMinerDataGetListener;
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

public class HttpUtil extends AsyncTask<String, Void, MinerDataHttpResult> {
    private final IMinerDataGetListener minerDataGetListener;
    private String httpResult = "";

    public HttpUtil(IMinerDataGetListener minerDataGetListener) {
        this.minerDataGetListener = minerDataGetListener;
    }

    @Override
    protected MinerDataHttpResult doInBackground(String... strings) {
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
            httpResult = sb.toString();
            return MinerDataHttpResult.SUCCESS;
        } catch (IOException e) {
            Log.e("Ethermine Pool Monitor", "Failed to get miner data from pool", e);
            return MinerDataHttpResult.FAILED_TO_DOWNLOAD;
        }
    }

    @Override
    protected void onPostExecute(MinerDataHttpResult result) {
        super.onPostExecute(result);

        if (result.equals(MinerDataHttpResult.FAILED_TO_DOWNLOAD)) {
            minerDataGetListener.onGetMinerDataHttpGetResult(result);
            return;
        }

        JSONObject data = null;
        try {
            data = new JSONObject(httpResult);
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            minerDataGetListener.onGetMinerDataHttpGetResult(result);
            return;
        }

        JSONObject minerStats = null;
        try {
            minerStats = data.getJSONObject("minerStats");
            MinerData.getInstance().setActiveWorkers(minerStats.getInt("activeWorkers"));
            MinerData.getInstance().setValidShares(minerStats.getInt("validShares"));
            MinerData.getInstance().setStaleShares(minerStats.getInt("staleShares"));
            MinerData.getInstance().setInvalidShares(minerStats.getInt("invalidShares"));
            MinerData.getInstance().setAddress(data.getString("address"));
            MinerData.getInstance().setHashrate(data.getString("hashRate"));
            MinerData.getInstance().setReportedHashrate(data.getString("reportedHashRate"));
            MinerData.getInstance().setAvarageHashrate(data.getDouble("avgHashrate"));
            MinerData.getInstance().setUnpaid(data.getDouble("unpaid"));

            JSONArray workerNames = data.getJSONObject("workers").names();
            JSONObject workers = data.getJSONObject("workers");
            List<Worker> workerList = new ArrayList<>();
            if (workerNames != null) {
                for (int i = 0; i < workerNames.length(); i++) {
                    workerList.add(Worker.fromJsonData(workers.getJSONObject(workerNames.getString(i))));
                }
            }
            MinerData.getInstance().setWorkers(workerList);

            JSONArray payouts = data.getJSONArray("payouts");
            List<Payout> payoutList = new ArrayList<>();
            if (payouts != null) {
                for (int i = 0; i < payouts.length(); i++) {
                    payoutList.add(Payout.fromJsonData(payouts.getJSONObject(i)));
                }
            }
            MinerData.getInstance().setPayouts(payoutList);

            JSONArray rounds = data.getJSONArray("rounds");
            List<Round> roundList = new ArrayList<>();
            if (rounds != null) {
                for (int i = rounds.length() - 1; i >= 0; i--) {
                    roundList.add(Round.fromJsonData(rounds.getJSONObject(i)));
                }
            }
            MinerData.getInstance().setRounds(roundList);

            JSONObject settings = data.getJSONObject("settings");
            MinerData.getInstance().setSettings(Settings.fromJsonData(settings));

            EstimatedEarnings estimatedEarnings = new EstimatedEarnings();
            double ethPerMin = 0, btcPerMin = 0, usdPerMin = 0;
            ethPerMin = data.getDouble("ethPerMin");
            estimatedEarnings.setEthPerMin(ethPerMin);
            usdPerMin = data.getDouble("usdPerMin");
            estimatedEarnings.setUsdPerMin(usdPerMin);
            btcPerMin = data.getDouble("btcPerMin");
            estimatedEarnings.setBtcPerMin(btcPerMin);
            MinerData.getInstance().setEstimatedEarnings(estimatedEarnings);
        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
            minerDataGetListener.onGetMinerDataHttpGetResult(MinerDataHttpResult.FAILED_TO_PARSE);
            return;
        }
        minerDataGetListener.onGetMinerDataHttpGetResult(MinerDataHttpResult.SUCCESS);
    }
}
