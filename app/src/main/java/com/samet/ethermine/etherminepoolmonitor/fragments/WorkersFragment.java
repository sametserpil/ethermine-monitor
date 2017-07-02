package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.adapters.WorkersListAdapter;
import com.samet.ethermine.etherminepoolmonitor.model.MinerData;

/**
 * Created by samet on 15.06.2017.
 */


public class WorkersFragment extends Fragment {

    private WorkersListAdapter listAdapter;
    private ExpandableListView expListView;

    public WorkersFragment() {
        // Required empty public constructor
    }

    public static WorkersFragment newInstance() {
        return new WorkersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers, container, false);
        // Inflate the layout for this fragment
        expListView = (ExpandableListView) view.findViewById(R.id.workers_list_view);
        listAdapter = new WorkersListAdapter(getContext(), MinerData.getInstance().getWorkers());

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

}
