package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.model.Payout;
import com.samet.ethermine.etherminepoolmonitor.model.PayoutListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PayoutsFragment extends Fragment implements OnChartValueSelectedListener {

    BarChart payoutsChart;

    public PayoutsFragment() {
    }

    public static PayoutsFragment newInstance() {
        return new PayoutsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payouts, container, false);
        ListView payoutsListView = (ListView) view.findViewById(R.id.payouts_list_view);
        List<Payout> payouts = new ArrayList<>();
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        payouts.add(new Payout(1, null, 1, 1, 99250997331903620L, null, "2017-06-17T03:10:24.000Z"));
        PayoutListAdapter adapter = new PayoutListAdapter(getContext(), R.layout.payout_list_item, payouts);
        payoutsListView.setAdapter(adapter);


        payoutsChart = (BarChart) view.findViewById(R.id.payouts_chart);
        payoutsChart.setOnChartValueSelectedListener(this);
        payoutsChart.setDrawBarShadow(false);
        payoutsChart.setDrawValueAboveBar(true);
        payoutsChart.getDescription().setEnabled(false);
        payoutsChart.setMaxVisibleValueCount(60);
        payoutsChart.setPinchZoom(false);
        payoutsChart.setDrawGridBackground(false);
        payoutsChart.setFitBars(true);

        XAxis xAxis = payoutsChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        payoutsChart.getAxisLeft().setEnabled(false);
        payoutsChart.getAxisRight().setEnabled(false);


        Legend l = payoutsChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        entries.add(new BarEntry(7f, 30f));
        entries.add(new BarEntry(8f, 80f));
        entries.add(new BarEntry(9f, 60f));
        entries.add(new BarEntry(10f, 50f));
        entries.add(new BarEntry(11f, 70f));
        entries.add(new BarEntry(12f, 60f));

        BarDataSet set = new BarDataSet(entries, "Last 100 Payouts");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData data = new BarData(set);
        payoutsChart.setData(data);
        payoutsChart.invalidate(); // refresh












        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        payoutsChart.animateY(2000);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected RectF mOnValueSelectedRectF = new RectF();

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        payoutsChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = payoutsChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + payoutsChart.getLowestVisibleX() + ", high: "
                        + payoutsChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }
}
