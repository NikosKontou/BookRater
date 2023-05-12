package com.example.bookrater;


import android.content.AsyncTaskLoader;
import android.content.Context;

import androidx.annotation.NonNull;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public BookLoader(@NonNull Context context) {
        super(context);
    }


    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);

    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }


    BookLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }



}
