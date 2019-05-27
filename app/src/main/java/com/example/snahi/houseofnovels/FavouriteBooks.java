package com.example.snahi.houseofnovels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favourite_books")
public class FavouriteBooks {
    @NonNull
    @PrimaryKey

    private String id;
    String title,author,imglink,publisher_name,publish_date,description;
    Float price;

    public FavouriteBooks() {
    }

    public FavouriteBooks(String id, String title, String author, String pulisher, String pulisherdate, String imglink, Float prc, String desc) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imglink = imglink;
        this.publisher_name = pulisher;
        this.publish_date = pulisherdate;
        price=prc;
        description=desc;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
