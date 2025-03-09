package com.example.filmfragments;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.filmfragments.model.Film;
import com.example.filmfragments.model.Genre;
import com.example.filmfragments.util.Unit;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AddFilmFragment extends Fragment {

    private EditText editTitle, editYear, editDesc;
    private Spinner spinnerGenre;
    private Button btnSave;
    private String selectedGenre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_film, container, false);

        editTitle = view.findViewById(R.id.editTitle);
        editYear = view.findViewById(R.id.editYear);
        editDesc = view.findViewById(R.id.editDesc);
        spinnerGenre = view.findViewById(R.id.spinnerGenre);
        btnSave = view.findViewById(R.id.btnSave);

        List<String> genres = Unit.getGenreRepository().findAll().stream().map(Genre::getName).collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_spinner_item, genres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGenre = genres.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedGenre = "";
            }
        });

        int id = getArguments() != null ? getArguments().getInt("id") : -1;

        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String yearStr = editYear.getText().toString().trim();
            String desc = editDesc.getText().toString().trim();

            if (title.isEmpty() || yearStr.isEmpty() || selectedGenre.isEmpty() || desc.isEmpty()) {
                Toast.makeText(inflater.getContext(), "Fill all of the fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = Integer.parseInt(yearStr);
            Film newFilm = new Film(title, Unit.getGenreRepository().find(selectedGenre), year, desc);

            if (id != -1) {
                Unit.getFilmRepository().updateFilm(newFilm, id);
            } else Unit.getFilmRepository().addFilm(newFilm);
            Toast.makeText(inflater.getContext(), "Success! ", Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_films_list);
        });

        if (id != -1) {
            Film film;
            try {
                film = Unit.getFilmRepository().find(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            editTitle.setText(film.getTitle());
            editDesc.setText(film.getDescription());
            editYear.setText(Integer.toString(film.getYear()));
            spinnerGenre.setSelection(Unit.getGenreRepository().getPosition(film.getGenre()));
        }

        return view;
    }
}
