package com.samet.ethermine.etherminepoolmonitor.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.samet.ethermine.etherminepoolmonitor.R;

import java.util.List;

/**
 * Created by samet on 17.06.2017.
 */

public class PayoutListAdapter extends ArrayAdapter<Payout> {
    public PayoutListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PayoutListAdapter(Context context, int resource, List<Payout> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.payout_list_item, null);
        }

        Payout payout = getItem(position);

        if (payout != null) {
            TextView amount_text_view = (TextView) v.findViewById(R.id.list_item_payout_amount);
            TextView date_text_view = (TextView) v.findViewById(R.id.list_item_payout_date);

            if (amount_text_view != null) {
                amount_text_view.setText(payout.getAmount() + " ETH");
            }

            if (date_text_view != null) {
                date_text_view.setText(payout.getPaidOn());
            }

        }

        return v;
    }
}
