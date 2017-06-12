package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.samet.ethermine.etherminepoolmonitor.R;

public class AddNewWalletFragment extends Fragment {
    private Button addWalletButton;


    public AddNewWalletFragment() {
        // Required empty public constructor
    }

    public static AddNewWalletFragment newInstance() {
        return new AddNewWalletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_wallet, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
