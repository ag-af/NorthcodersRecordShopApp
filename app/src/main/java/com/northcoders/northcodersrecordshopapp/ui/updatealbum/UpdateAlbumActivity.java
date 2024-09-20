package com.northcoders.northcodersrecordshopapp.ui.updatealbum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.northcodersrecordshopapp.R;
import com.northcoders.northcodersrecordshopapp.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.model.Genre;
import com.northcoders.northcodersrecordshopapp.ui.addalbum.AddAlbumClickHandlers;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivity;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private MainActivityViewModel viewModel;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddNewAlbumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        album = (Album) getIntent().getSerializableExtra("album");

        if (album != null) {
            binding.editTitle.setText(album.getTitle());
            binding.editArtist.setText(album.getArtist());
            binding.editReleaseYear.setText(String.valueOf(album.getReleaseYear()));
            binding.editPrice.setText(String.valueOf(album.getPrice()));
            binding.editStock.setText(String.valueOf(album.getStock()));

            setupGenreSpinner(album.getGenre());
        }
    }

        private void setupGenreSpinner(Genre genre) {

            Spinner spinner = binding.spinnerGenre;
            String[] genres = getResources().getStringArray(R.array.genre_array);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            if (genre != null) {
                int genrePosition = adapter.getPosition(genre.name());
                spinner.setSelection(genrePosition);
            }
    }

    private void updateAlbum() {
        album.setTitle(binding.editTitle.getText().toString());
        album.setArtist(binding.editArtist.getText().toString());
        album.setReleaseYear(binding.editReleaseYear.getText().toString());
        album.setPrice(binding.editPrice.getText().toString());
        album.setStock(binding.editStock.getText().toString());

        String genreString = (String) binding.spinnerGenre.getSelectedItem();
        album.setGenre(Genre.valueOf(genreString));

        viewModel.updateAlbum(album.getId(), album);
    }



}