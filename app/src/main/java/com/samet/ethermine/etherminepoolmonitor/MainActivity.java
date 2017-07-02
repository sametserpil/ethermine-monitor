package com.samet.ethermine.etherminepoolmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samet.ethermine.etherminepoolmonitor.fragments.AddNewWalletFragment;
import com.samet.ethermine.etherminepoolmonitor.fragments.CalculatorFragment;
import com.samet.ethermine.etherminepoolmonitor.fragments.DashboardFragment;
import com.samet.ethermine.etherminepoolmonitor.fragments.PayoutsFragment;
import com.samet.ethermine.etherminepoolmonitor.fragments.WalletsFragment;
import com.samet.ethermine.etherminepoolmonitor.fragments.WorkersFragment;
import com.samet.ethermine.etherminepoolmonitor.misc.IMinerDataGetListener;
import com.samet.ethermine.etherminepoolmonitor.model.MinerData;
import com.samet.ethermine.etherminepoolmonitor.network.HttpUtil;
import com.samet.ethermine.etherminepoolmonitor.network.MinerDataHttpResult;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMinerDataGetListener {

    private TextView mainScreenTextView;
    private TextView walletIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainScreenTextView = (TextView) findViewById(R.id.main_screen_textview);
        String activeWalletId = getSavedMinerId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        walletIdTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.header_current_wallet_text_view);
        if (activeWalletId.equals(getString(R.string.unknown))) {
            mainScreenTextView.setText(getString(R.string.no_wallet_selected));
        } else {
            new HttpUtil(this).execute(getString(R.string.get_miner_data_url_prefix) + activeWalletId);
        }
        walletIdTextView.setText(activeWalletId);
    }

    private String getSavedMinerId() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.wallet_id_shared_pref_file), Context.MODE_PRIVATE);
        return sharedPref.getString(getString(R.string.active_wallet_id), getString(R.string.unknown));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_wallet) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, AddNewWalletFragment.newInstance(), getString(R.string.add_wallet)).commit();
        } else if (id == R.id.nav_dashboard) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, DashboardFragment.newInstance()).commit();
        } else if (id == R.id.nav_workers) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, WorkersFragment.newInstance()).commit();
        } else if (id == R.id.nav_payouts) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, PayoutsFragment.newInstance()).commit();
        } else if (id == R.id.nav_calculator) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, CalculatorFragment.newInstance()).commit();
        } else if (id == R.id.change_wallet) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, WalletsFragment.newInstance(), getString(R.string.change_wallet)).commit();
        }

        mainScreenTextView.setText("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onGetMinerDataHttpGetResult(MinerDataHttpResult result) {
        switch (result) {
            case FAILED_TO_DOWNLOAD:
                mainScreenTextView.setText(getString(R.string.failed_to_download));
                break;
            case FAILED_TO_PARSE:
                mainScreenTextView.setText(getString(R.string.failed_to_parse));
                break;
            case SUCCESS:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_holder, DashboardFragment.newInstance()).commit();
                break;
        }
        walletIdTextView.setText(MinerData.getInstance().getAddress());
    }

}
