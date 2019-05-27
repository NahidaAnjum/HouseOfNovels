package com.example.snahi.houseofnovels;

public class BookModel {
    String id, title, author, imglink, publisher_name, publish_date, description;
    Float price;

    public BookModel() {
    }

    public BookModel(String id, String title, String author, String pulisher, String pulisherdate, String imglink, Float prc, String desc) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imglink = imglink;
        this.publisher_name = pulisher;
        this.publish_date = pulisherdate;
        price = prc;
        description = desc;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setOthr(String othr) {
        this.author= author;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


}


