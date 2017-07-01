package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.fragments.dummy.DummyContent;
import com.samet.ethermine.etherminepoolmonitor.model.MyWalletRecyclerViewAdapter;

public class WalletsFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_wallets_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyWalletRecyclerViewAdapter(DummyContent.ITEMS));
        }
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
