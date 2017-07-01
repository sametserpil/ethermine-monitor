package com.samet.ethermine.etherminepoolmonitor.misc;

import com.samet.ethermine.etherminepoolmonitor.network.MinerDataHttpResult;

/**
 * Created by samet on 1.07.2017.
 */

public interface IMinerDataGetListener {

    void onGetMinerDataHttpGetResult(MinerDataHttpResult result);
}
