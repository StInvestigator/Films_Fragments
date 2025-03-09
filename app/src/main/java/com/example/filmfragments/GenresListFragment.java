package com.example.filmfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.filmfragments.databinding.FragmentFilmsListBinding;
import com.example.filmfragments.databinding.FragmentGenresListBinding;
import com.example.filmfragments.util.Unit;

import java.sql.SQLException;

public class GenresListFragment extends ListFragment {

    private FragmentGenresListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGenresListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Unit.setGenreActivity(requireActivity());
        setListAdapter(Unit.getGenreRepository().getGenreArrayAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}