package com.samet.ethermine.etherminepoolmonitor.network;


import android.os.AsyncTask;
import android.util.Log;

import com.samet.ethermine.etherminepoolmonitor.model.MinerData;
import com.samet.ethermine.etherminepoolmonitor.model.Payout;

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
        try {
            JSONObject data = new JSONObject(result);
            MinerData.getInstance().setAddress(data.getString("address"));
            MinerData.getInstance().setHashrate(data.getString("hashRate"));
            MinerData.getInstance().setReportedHashrate(data.getString("reportedHashRate"));
            MinerData.getInstance().setAvarageHashrate(data.getDouble("avgHashrate"));
/*
            JSONArray workers = data.getJSONArray("workers");
            List<Worker> workerList = new ArrayList<>();
            for (int i = 0; i < workers.length(); i++) {
                workerList.add(Worker.fromJsonData(workers.getJSONObject(i)));
            }
            MinerData.getInstance().setWorkers(workerList);
*/
            JSONArray payouts = data.getJSONArray("payouts");
            List<Payout> payoutList = new ArrayList<>();
            for (int i = 0; i < payouts.length(); i++) {
                payoutList.add(Payout.fromJsonData(payouts.getJSONObject(i)));
            }
            MinerData.getInstance().setPayouts(payoutList);

        } catch (JSONException e) {
            Log.e("Ethermine Pool Monitor", "Failed to parse http request reuslt", e);
        }
    }
}
