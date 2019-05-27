package com.example.snahi.houseofnovels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {
    private MyDao myDao;
    private LiveData<List<FavouriteBooks>> getdataa;

    public DataRepository(Application application) {
        MyDataBase myDataBase = MyDataBase.getDatabase(application);
        myDao = myDataBase.myDaoDatabase();
        getdataa = myDao.getAlldata();
    }

    public LiveData<List<FavouriteBooks>> getrepository() {
        return getdataa;
    }

    public void insert(FavouriteBooks favouriteBooks) {
        new AsynctaskForInsert(myDao).execute(favouriteBooks);
    }

    private class AsynctaskForInsert extends AsyncTask<FavouriteBooks, Void, Void> {
        MyDao myDao;

        public AsynctaskForInsert(MyDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(FavouriteBooks... favouriteBooks) {
            myDao.insert(favouriteBooks[0]);
            return null;
        }
    }

    public void delete(FavouriteBooks favouriteBooks) {
        new AsynctaskForDelete(myDao).execute(favouriteBooks);
    }

    private class AsynctaskForDelete extends AsyncTask<FavouriteBooks, Void, Void> {
        MyDao myDao;

        public AsynctaskForDelete(MyDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(FavouriteBooks... favouriteBooks) {
            myDao.Delete(favouriteBooks[0]);
            return null;
        }
    }
}


