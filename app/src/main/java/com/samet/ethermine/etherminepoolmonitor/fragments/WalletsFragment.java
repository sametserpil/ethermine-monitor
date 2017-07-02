package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.adapters.WalletsListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WalletsFragment extends Fragment {

    private WalletsListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> wallets;

    public WalletsFragment() {
    }

    public static WalletsFragment newInstance() {
        return new WalletsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallets, container, false);
        wallets = getWallets();
        // Inflate the layout for this fragment
        expListView = (ExpandableListView) view.findViewById(R.id.wallets_list_view);
        listAdapter = new WalletsListAdapter(getContext(), wallets);

        // setting list adapter
        expListView.setAdapter(listAdapter);
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

    private List<String> getWallets() {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.wallet_id_shared_pref_file), Context.MODE_PRIVATE);
        return new ArrayList<String>(sharedPref.getStringSet(getString(R.string.wallet_ids), new HashSet<String>()));
    }
}
