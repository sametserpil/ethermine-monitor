package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.model.MinerData;


public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ((TextView) view.findViewById(R.id.dashboard_reported_hashrate_val)).setText(MinerData.getInstance().getReportedHashrate());
        ((TextView) view.findViewById(R.id.dashboard_current_hashrate_val)).setText(MinerData.getInstance().getHashrate());
        ((TextView) view.findViewById(R.id.dashboard_avarage_hashrate_val)).setText(MinerData.getInstance().getFormattedAvarageHashrate());
        ((TextView) view.findViewById(R.id.active_workers_textview)).setText(Integer.toString(MinerData.getInstance().getWorkers().size()));
        ((TextView) view.findViewById(R.id.unpaid_balance_textview)).setText(MinerData.getInstance().getFormattedUnpaid());
        ((TextView) view.findViewById(R.id.dashboard_valid_shares_val)).setText(Integer.toString(MinerData.getInstance().getValidShares()));
        ((TextView) view.findViewById(R.id.dashboard_stale_shares_val)).setText(Integer.toString(MinerData.getInstance().getStaleShares()));
        ((TextView) view.findViewById(R.id.dashboard_invalid_shares_val)).setText(Integer.toString(MinerData.getInstance().getInvalidShares()));


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
