package com.example.filmfragments;

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

import java.util.List;
import java.util.stream.Collectors;

public class AddGenreFragment extends Fragment {

    private EditText editName;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_genre, container, false);

        editName = view.findViewById(R.id.editName);
        btnSave = view.findViewById(R.id.btnSave);

        int id = getArguments() != null ? getArguments().getInt("id") : -1;

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(inflater.getContext(), "Fill all of the fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            Genre genre = new Genre(name);

            if(id!=-1){
                Unit.getGenreRepository().updateGenre(genre, id);
            }
            else Unit.getGenreRepository().addGenre(genre);
            Toast.makeText(inflater.getContext(), "Success! ", Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_genres_list);
        });

        if (id != -1) {
            editName.setText(Unit.getGenreRepository().find(id).getName());
        }

        return view;
    }
}
