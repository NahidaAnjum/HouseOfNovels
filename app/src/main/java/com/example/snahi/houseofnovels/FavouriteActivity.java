package com.example.snahi.houseofnovels;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView fav_rv;
    FavouritemodelView favouritemodelView;
    List<FavouriteBooks> favoratebooks_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        fav_rv = findViewById(R.id.rv_fav);
        favouritemodelView = ViewModelProviders.of(this)
                .get(FavouritemodelView.class);

        favouriteBooks();
        Toast.makeText(this, R.string.wlcm_fav, Toast.LENGTH_SHORT).show();
    }

    private void favouriteBooks() {
        favouritemodelView.getviewdata().observe(this, new Observer<List<FavouriteBooks>>() {
            @Override
            public void onChanged(@Nullable List<FavouriteBooks> favouriteBooks) {
                favoratebooks_s = favouriteBooks;
               FavouriteAdapter favouriteAdapter = new FavouriteAdapter(FavouriteActivity.this, (List<FavouriteBooks>) favouriteBooks);
                fav_rv.setAdapter(favouriteAdapter);
                fav_rv.setLayoutManager(new LinearLayoutManager(FavouriteActivity.this));

            }
        });
    }
}

