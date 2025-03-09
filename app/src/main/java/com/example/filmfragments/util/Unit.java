package com.example.filmfragments.util;

import androidx.fragment.app.FragmentActivity;

import com.example.filmfragments.domain.FilmSQLHelper;
import com.example.filmfragments.domain.repository.FilmRepository;
import com.example.filmfragments.domain.repository.GenreRepository;

import java.sql.SQLException;

public class Unit {
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    private static final Unit instance;

    static{
        try {
            instance = new Unit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Unit() throws SQLException {
        FilmSQLHelper filmSQLHelper = new FilmSQLHelper();
        filmRepository = new FilmRepository(filmSQLHelper);
        genreRepository = new GenreRepository(filmSQLHelper);
    }

    public static FilmRepository getFilmRepository() {
        return instance.filmRepository;
    }

    public static void setFilmActivity(FragmentActivity activity){
        instance.filmRepository.setActivity(activity);
    }

    public static GenreRepository getGenreRepository() {
        return instance.genreRepository;
    }

    public static void setGenreActivity(FragmentActivity activity){
        instance.genreRepository.setActivity(activity);
    }
}
