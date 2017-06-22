package com.samet.ethermine.etherminepoolmonitor.network;


import android.os.AsyncTask;
import android.util.Log;

import com.samet.ethermine.etherminepoolmonitor.model.MinerData;
import com.samet.ethermine.etherminepoolmonitor.model.Payout;
import com.samet.ethermine.etherminepoolmonitor.model.Round;
import com.samet.ethermine.etherminepoolmonitor.model.Settings;
import com.samet.ethermine.etherminepoolmonitor.model.Worker;

import org.json.JSONArray;
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
        try {
            JSONObject data = new JSONObject(result);
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


        } catch (Exception e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
    }
}
