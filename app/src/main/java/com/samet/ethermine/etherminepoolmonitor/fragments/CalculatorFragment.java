package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.model.MinerData;

public class CalculatorFragment extends Fragment implements MaterialSpinner.OnItemSelectedListener {

    private TextView ethPerMinTextView;
    private TextView btcPerMinTextView;
    private TextView usdPerMinTextView;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment newInstance() {
        return new CalculatorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ethPerMinTextView = (TextView) view.findViewById(R.id.eth_per_min_textview);
        btcPerMinTextView = (TextView) view.findViewById(R.id.btc_per_min_textview);
        usdPerMinTextView = (TextView) view.findViewById(R.id.usd_per_min_textview);

        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.income_schedule_spinner);
        spinner.setItems("Every Minute", "Hourly", "Daily", "Weekly", "Monthly");
        spinner.setOnItemSelectedListener(this);
        setIncomePerMin();
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

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        switch (position) {
            case 0:
                setIncomePerMin();
                break;
            case 1:
                ethPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedEthPerHour());
                btcPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedBtcPerHour());
                usdPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedUsdPerHour());
                break;
            case 2:
                ethPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedEthPerDay());
                btcPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedBtcPerDay());
                usdPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedUsdPerDay());
                break;
            case 3:
                ethPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedEthPerWeek());
                btcPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedBtcPerWeek());
                usdPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedUsdPerWeek());
                break;
            case 4:
                ethPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedEthPerMonth());
                btcPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedBtcPerMonth());
                usdPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedUsdPerMonth());
                break;
        }
    }

    private void setIncomePerMin() {
        ethPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedEthPerMin());
        btcPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedBtcPerMin());
        usdPerMinTextView.setText(MinerData.getInstance().getEstimatedEarnings().getFormattedUsdPerMin());
    }
}
