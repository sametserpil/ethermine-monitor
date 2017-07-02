package com.samet.ethermine.etherminepoolmonitor.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.misc.IMinerDataGetListener;
import com.samet.ethermine.etherminepoolmonitor.network.HttpUtil;

import java.util.HashSet;
import java.util.List;

/**
 * Created by samet on 2.07.2017.
 */

public class WalletsListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> wallets;

    public WalletsListAdapter(Context context, List<String> wallets) {
        this._context = context;
        this.wallets = wallets;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.wallets.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.wallets_list_view_child, null);
        }

        convertView.findViewById(R.id.set_wallet_active_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity fragmentActivity = (FragmentActivity) view.getContext();
                fragmentActivity.getSupportFragmentManager().beginTransaction()
                        .remove(fragmentActivity.getSupportFragmentManager().findFragmentByTag(_context.getString(R.string.change_wallet)))
                        .commit();
                changeActiveWallet(groupPosition);
            }
        });
        convertView.findViewById(R.id.remove_wallet_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeWallet(groupPosition);
                FragmentActivity fragmentActivity = (FragmentActivity) view.getContext();
                fragmentActivity.getSupportFragmentManager().beginTransaction()
                        .remove(fragmentActivity.getSupportFragmentManager().findFragmentByTag(_context.getString(R.string.change_wallet)))
                        .commit();
            }
        });
        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.wallets.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return wallets.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String worker = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.wallets_list_view_header, null);
        }
        ((TextView) convertView.findViewById(R.id.wallets_header_textview)).setText(worker);

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


    private void changeActiveWallet(int pos) {
        SharedPreferences sharedPref = _context.getSharedPreferences(
                _context.getString(R.string.wallet_id_shared_pref_file), Context.MODE_PRIVATE);
        sharedPref.edit().putString(_context.getString(R.string.active_wallet_id), wallets.get(pos)).apply();
        Toast.makeText(_context, _context.getString(R.string.changed_active_wallet), Toast.LENGTH_SHORT).show();
        new HttpUtil((IMinerDataGetListener) _context).execute(_context.getString(R.string.get_miner_data_url_prefix) + wallets.get(pos));
    }

    private void removeWallet(int pos) {
        wallets.remove(pos);
        SharedPreferences sharedPref = _context.getSharedPreferences(
                _context.getString(R.string.wallet_id_shared_pref_file), Context.MODE_PRIVATE);
        sharedPref.edit().putStringSet(_context.getString(R.string.wallet_ids), new HashSet<>(wallets)).apply();
        Toast.makeText(_context, _context.getString(R.string.removed_wallet), Toast.LENGTH_SHORT).show();
    }
}
