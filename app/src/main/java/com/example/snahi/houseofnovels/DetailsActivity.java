package com.example.snahi.houseofnovels;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    SlidrInterface slidrInterface;
    ImageView imageView;
    //String myId;
    TextView favo,title2, author2, publisher2, publisherdate2, prcice, desc;
    String title, description;
    String image;
    String author;
    String date, id;
    String publisher;
    Float price;
    FavouritemodelView favouritemodelView;
    LikeButton favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.image_tv);
        favo=findViewById(R.id.fav_tv);
        desc = findViewById(R.id.dec_tv);
        title2 = findViewById(R.id.title_tv);
        author2 = findViewById(R.id.author_tv);
        publisher2 = findViewById(R.id.publisher_tv);
        publisherdate2 = findViewById(R.id.publisheddate_tv);
        favourite = findViewById(R.id.fv_btn);
        prcice = findViewById(R.id.price_tv);

        favouritemodelView = ViewModelProviders.of(this).get(FavouritemodelView.class);
        slidrInterface = Slidr.attach(this);
        slidrInterface = Slidr.attach(this);

        Intent intent = getIntent();
       id = intent.getStringExtra("key");
        title = intent.getStringExtra("title");
        author = intent.getStringExtra("author");
        date = intent.getStringExtra("p_date");
        publisher = intent.getStringExtra("publisher");
        description = intent.getStringExtra("desc");
        price = intent.getFloatExtra("price", 0);
       // Toast.makeText(this, "" + price, Toast.LENGTH_SHORT).show();
        image = intent.getStringExtra("image");
        title2.setText(title);
        author2.setText(author);
        publisher2.setText(publisher);
        publisherdate2.setText(date);
        prcice.setText("" + price);
        desc.setText(description);
        Picasso.with(DetailsActivity.this).load(image).into(imageView);
        favCheckingStatus();
        favourite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                FavouriteBooks fav_books = new
                        FavouriteBooks(id, title, author, publisher, date, image, price, description);
                favouritemodelView.insertFromViewModel(fav_books);
                Toast.makeText(DetailsActivity.this, R.string.fav, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                FavouriteBooks fav_books = new FavouriteBooks(
                        id, title, author, publisher, date, image, price, description);
                favouritemodelView.deleteFromViewModel(fav_books);
                Toast.makeText(DetailsActivity.this, R.string.rmv_fav, Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void favCheckingStatus() {
       favouritemodelView.getviewdata().observe
                (this, new Observer<List<FavouriteBooks>>() {
                    public void onChanged(@Nullable List<FavouriteBooks> fav_books) {
                        for (int i = 0; i < fav_books.size(); i++) {
                            String status = fav_books.get(i).getId();
                            if (status.equalsIgnoreCase(id)) {
                                favourite.setLiked(true);
                            }
                        }
                    }
                });
   }

}

