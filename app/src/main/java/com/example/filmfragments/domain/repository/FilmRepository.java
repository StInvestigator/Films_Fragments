package com.example.filmfragments.domain.repository;

import androidx.fragment.app.FragmentActivity;

import com.example.filmfragments.adapter.FilmArrayAdapter;
import com.example.filmfragments.domain.FilmSQLHelper;
import com.example.filmfragments.model.Film;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class FilmRepository extends BaseDaoImpl<Film, Integer> {

    private FragmentActivity activity;


    public FilmRepository(FilmSQLHelper sqlHelper) throws SQLException {
        super(sqlHelper.getConnectionSource(), Film.class);
    }

    public Film find(Integer id) throws SQLException {
        return queryForId(id);
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public FilmArrayAdapter getFilmArrayAdapter() throws SQLException {
        FilmArrayAdapter adapter = new FilmArrayAdapter(activity);
        adapter.addAll(queryForAll());
        return adapter;
    }

    public void addFilm(Film film) {
        try {
            this.create(film);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFilm(Film film, int id) {
        film.setId(id);
        try {
            this.update(film);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFilm(int id) throws SQLException {
        deleteById(id);
    }
}
