package com.example.snahi.houseofnovels;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Query("select * from favourite_books")
    LiveData<List<FavouriteBooks>> getAlldata();

    @Insert
    void insert(FavouriteBooks favouriteBooks);

    @Delete
    void Delete(FavouriteBooks favouriteBooks);
}
