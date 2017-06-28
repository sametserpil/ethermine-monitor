package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

        TextInputEditText textInput = (TextInputEditText) view.findViewById(R.id.wallet_address_input);
        textInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
