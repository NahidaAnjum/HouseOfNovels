package com.example.snahi.houseofnovels;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    FirebaseAuth mfirebase;
    ConnectivityManager connectivityManager;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfirebase = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.rv);
        adView = findViewById(R.id.adView);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


        MobileAds.initialize(this, getString(R.string.Ad));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.show_coupon_fab);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Connectionestablished()) {

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null) {
                sendtologin();
            }
        } else {
            CheckInternetConnection();
        }

    }

    private void sendtologin() {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void Romanceclick(View view) {

        Intent romance = new Intent(this, RomanceIntentActivity.class);
        startActivity(romance);
    }

    public void SciencefictionClick(View view) {

        Intent scifi = new Intent(this, ScienceFictionActivity.class);
        startActivity(scifi);
    }


    public void horrorclick(View view) {
        Intent horror = new Intent(this, HorrorActivity.class);
        startActivity(horror);
    }


    public void fantasyclick(View view) {
        Intent fantasy = new Intent(this, FantasyActivity.class);
        startActivity(fantasy);
    }


    public void comedyclick(View view) {
        Intent comedy = new Intent(this, ComedyActivity.class);
        startActivity(comedy);
    }

    public void thrillerclick(View view) {
        Intent thriller = new Intent(this, ThrillerActivity.class);
        startActivity(thriller);
    }

    public void historicalfictionclick(View view) {
        Intent hisfi = new Intent(this, HistoricalActivity.class);
        startActivity(hisfi);
    }
    public void dramaclick(View view) {
        Intent drama= new Intent(this, DramaActivity.class);
        startActivity(drama);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favourite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fav:

                Intent intent = new Intent(this, FavouriteActivity.class);
                startActivity(intent);

                break;
            case R.id.log:
                logout();

                break;
        }
        return true;
    }

    private void logout() {
        mfirebase.signOut();
        sendtologin();
    }


    public void fav(View view) {
        Intent intent = new Intent(this, FavouriteActivity.class);
        startActivity(intent);
    }

    private boolean Connectionestablished() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    private void CheckInternetConnection() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_msg);
        builder.setTitle(R.string.no_internet);
        builder.setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }).show();
    }
}