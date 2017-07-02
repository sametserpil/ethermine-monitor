package com.samet.ethermine.etherminepoolmonitor.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.samet.ethermine.etherminepoolmonitor.R;
import com.samet.ethermine.etherminepoolmonitor.misc.IMinerDataGetListener;
import com.samet.ethermine.etherminepoolmonitor.network.HttpUtil;

import java.util.HashSet;
import java.util.Set;

public class AddNewWalletFragment extends Fragment implements View.OnClickListener {
    private Button addWalletButton;
    private TextInputEditText walletIdTextInput;


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
        addWalletButton = (Button) view.findViewById(R.id.add_Wallet_button);
        addWalletButton.setOnClickListener(this);

        walletIdTextInput = (TextInputEditText) view.findViewById(R.id.wallet_address_input);
        walletIdTextInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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


    @Override
    public void onClick(View view) {
        String walletId = walletIdTextInput.getText().toString();
        if (walletId.startsWith("0x")) {
            walletId = walletId.substring(2);
        } else if (walletId.equals("")) {
            Toast.makeText(getContext(), getString(R.string.please_enter_wallet_address), Toast.LENGTH_SHORT).show();
            return;
        }
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.wallet_id_shared_pref_file), Context.MODE_PRIVATE);

        String activeWalletId = sharedPref.getString(getString(R.string.active_wallet_id), getString(R.string.unknown));
        if (activeWalletId.equals(getString(R.string.unknown))) {
            sharedPref.edit().putString(getString(R.string.active_wallet_id), walletId).apply();
            new HttpUtil((IMinerDataGetListener) getActivity()).execute(getString(R.string.get_miner_data_url_prefix) + walletId);
        }
        Set<String> walletIds = sharedPref.getStringSet(getString(R.string.wallet_ids), new HashSet<String>());
        walletIds.add(walletId);
        sharedPref.edit().putStringSet(getString(R.string.wallet_ids), walletIds).apply();

        walletIdTextInput.setText("");
        Toast.makeText(getContext(), getString(R.string.added_new_wallet_address), Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentByTag(getString(R.string.add_wallet))).commit();
    }
}
