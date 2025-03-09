package com.example.filmfragments.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.filmfragments.AddFilmFragment;
import com.example.filmfragments.FilmApplication;
import com.example.filmfragments.R;
import com.example.filmfragments.model.Film;
import com.example.filmfragments.util.Unit;

import java.sql.SQLException;

public class FilmArrayAdapter extends ArrayAdapter<Film> {

    private FragmentActivity activity;

    public FilmArrayAdapter(FragmentActivity activity) {
        super(FilmApplication.getApplication(), R.layout.film_layout, R.id.film_title);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Film film = getItem(position);
        ((TextView) view.findViewById(R.id.film_title)).setText(film.getTitle());
        ((TextView) view.findViewById(R.id.film_genre)).setText(film.getGenre());
        ((TextView) view.findViewById(R.id.film_year)).setText(String.format("%d year", film.getYear()));
        view.findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main);
            Bundle bundle = new Bundle();
            bundle.putInt("id", film.getId());
            navController.navigate(R.id.navigation_add_film, bundle);

        });

        view.findViewById(R.id.btnDelete).setOnClickListener(v -> {
            try {
                Unit.getFilmRepository().deleteFilm(film.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            remove(film);
            notifyDataSetChanged();
        });

        return view;
    }
}
