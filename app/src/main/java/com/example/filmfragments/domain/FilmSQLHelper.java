package com.example.filmfragments.domain;

import android.database.sqlite.SQLiteDatabase;

import com.example.filmfragments.FilmApplication;
import com.example.filmfragments.model.Film;
import com.example.filmfragments.model.Genre;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class FilmSQLHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "films.db";
    private static final int DATABASE_VERSION = 1;

    public FilmSQLHelper() {
        super(FilmApplication.getApplication(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Genre.class);
            TableUtils.createTable(connectionSource, Film.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table genres(id integer primary key autoincrement, name text not null)");
//        db.execSQL("create table films(id integer primary key autoincrement, title text not null, description text," +
//                "year integer, genre_id integer, foreign key (genre_id) references genres (id))");
//
//        db.execSQL("insert into genres(name) values ('Drama'),('Comedy'),('Action'),('Fiction'),('Horror')");
//        db.execSQL("insert into films(title, description, year, genre_id) values" +
//                "('super film 1','This is just your regular ultra interesting film', 2005, 1)," +
//                "('super film 2','This is just your regular ultra interesting film`s sequel', 2006, 1)," +
//                "('super film 3','This is just your regular ultra interesting film`s triquel', 2007, 4)");
//    }
}
