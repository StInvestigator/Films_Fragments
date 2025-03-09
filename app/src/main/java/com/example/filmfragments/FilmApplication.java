package com.example.filmfragments;

import android.app.Application;

public class FilmApplication extends Application {
    private static Application _app;
    public static Application getApplication(){
        return _app;
    }

    public FilmApplication(){
        _app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _app = this;
    }
}
