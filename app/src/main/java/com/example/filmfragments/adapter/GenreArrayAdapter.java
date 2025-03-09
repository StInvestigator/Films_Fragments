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
import com.example.filmfragments.AddGenreFragment;
import com.example.filmfragments.FilmApplication;
import com.example.filmfragments.R;
import com.example.filmfragments.model.Genre;
import com.example.filmfragments.util.Unit;

public class GenreArrayAdapter extends ArrayAdapter<Genre> {
    private FragmentActivity activity;
    public GenreArrayAdapter(FragmentActivity activity) {
        super(FilmApplication.getApplication(), R.layout.genre_layout, R.id.genre_name);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Genre genre = getItem(position);
        ((TextView)view.findViewById(R.id.genre_name)).setText(genre.getName());

        view.findViewById(R.id.btnUpdate).setOnClickListener(v->{
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main);
            Bundle bundle = new Bundle();
            bundle.putInt("id", genre.getId());
            navController.navigate(R.id.navigation_add_genre, bundle);
        });

        view.findViewById(R.id.btnDelete).setOnClickListener(v->{
            Unit.getGenreRepository().deleteGenre(genre.getId());
            remove(genre);
            notifyDataSetChanged();
        });

        return view;
    }
}
