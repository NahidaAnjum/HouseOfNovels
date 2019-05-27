package com.example.snahi.houseofnovels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavouritemodelView extends AndroidViewModel {
    private DataRepository dataRepository;
    private LiveData<List<FavouriteBooks>> mydata;

    public FavouritemodelView(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        mydata = dataRepository.getrepository();
    }

    public LiveData<List<FavouriteBooks>> getviewdata() {
        return mydata;
    }

    public void insertFromViewModel(FavouriteBooks favouriteBooks) {
        dataRepository.insert(favouriteBooks);
    }

    public void deleteFromViewModel(FavouriteBooks favouriteBooks) {
        dataRepository.delete(favouriteBooks);
    }
}
