package com.samet.ethermine.etherminepoolmonitor.model;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.misc.Utils;

import java.util.List;

/**
 * Created by samet on 16.06.2017.
 */

public class WorkersListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Worker> workers;

    public WorkersListAdapter(Context context, List<Worker> workers
    ) {
        this._context = context;
        this.workers = workers;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.workers.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Worker worker = (Worker) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.workers_list_view_child, null);
        }

        TextView currentHasrate = (TextView) convertView
                .findViewById(R.id.current_hashrate_val);
        currentHasrate.setText(worker.getHashRate());


        TextView reportedHashrate = (TextView) convertView
                .findViewById(R.id.reported_hashrate_val);
        reportedHashrate.setText(worker.getReportedHashRate());

        TextView validShares = (TextView) convertView
                .findViewById(R.id.valid_shares_val);
        validShares.setText(Integer.toString(worker.getValidShares()));

        TextView staleShares = (TextView) convertView
                .findViewById(R.id.stale_shares_val);
        staleShares.setText(Integer.toString(worker.getStaleShares()));

        TextView invalidShares = (TextView) convertView
                .findViewById(R.id.invalid_shares_val);
        invalidShares.setText(Integer.toString(worker.getInvalidShares()));

        TextView lastSeen = (TextView) convertView
                .findViewById(R.id.last_seen_val);
        lastSeen.setText(Utils.longToDateString(worker.getWorkerLastSubmitTime()));

        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.workers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return MinerData.getInstance().getActiveWorkers();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Worker worker = (Worker) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.workers_list_view_header, null);
        }

        TextView list_item_header_miner_name_text_view = (TextView) convertView
                .findViewById(R.id.list_item_header_miner_name_text_view);
        list_item_header_miner_name_text_view.setTypeface(null, Typeface.BOLD);
        list_item_header_miner_name_text_view.setText(worker.getName());

        TextView list_item_header_hash_rate_text_view = (TextView) convertView.findViewById(R.id.list_item_header_hash_rate_text_view);
        list_item_header_hash_rate_text_view.setText(worker.getHashRate());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}