package com.northcoders.northcodersrecordshopapp.ui.updatealbum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.northcodersrecordshopapp.R;
import com.northcoders.northcodersrecordshopapp.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.model.Genre;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivity;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private ActivityUpdateAlbumBinding binding;
    private MainActivityViewModel viewModel;
    private Album selectedAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateAlbumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        selectedAlbum = getIntent().getParcelableExtra("selected_album");

        Spinner genreSpinner = findViewById(R.id.spinnerGenre);

        if (selectedAlbum == null) {
            Toast.makeText(this, "No album found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            binding.setAlbum(selectedAlbum);

            binding.editTitle.setText(selectedAlbum.getTitle());
            binding.editArtist.setText(selectedAlbum.getArtist());
            binding.editReleaseYear.setText(String.valueOf(selectedAlbum.getReleaseYear()));
            binding.editPrice.setText(String.valueOf(selectedAlbum.getPrice()));
            binding.editStock.setText(String.valueOf(selectedAlbum.getStock()));

            String[] genres = getResources().getStringArray(R.array.genre_array);
            int genreIndex = getGenreIndex(selectedAlbum.getGenre().name(), genres);
            genreSpinner.setSelection(genreIndex);
        }


        UpdateAlbumClickHandlers clickHandlers = new UpdateAlbumClickHandlers(selectedAlbum, this, viewModel, binding.spinnerGenre, binding);
        binding.setClickHandler(clickHandlers);
        binding.setAlbum(selectedAlbum);

    }

        private int getGenreIndex(String genre, String[] genres) {

            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equalsIgnoreCase(genre)) {
                    return i;
                }
            }
            return 0;
    }

    private void updateAlbum(Album selectedAlbum) {
        selectedAlbum.setTitle(binding.editTitle.getText().toString());
        selectedAlbum.setArtist(binding.editArtist.getText().toString());
        selectedAlbum.setReleaseYear(binding.editReleaseYear.getText().toString());
        selectedAlbum.setPrice(binding.editPrice.getText().toString());
        selectedAlbum.setStock(binding.editStock.getText().toString());

        String genreString = (String) binding.spinnerGenre.getSelectedItem();
        selectedAlbum.setGenre(Genre.valueOf(genreString));

        viewModel.updateAlbum(selectedAlbum.getId(), selectedAlbum);
        Toast.makeText(this, "Album updated successfully", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(UpdateAlbumActivity.this, MainActivity.class));
    }

    private void deleteAlbum(Album selectedAlbum) {
        viewModel.deleteAlbum(selectedAlbum.getId());
        Toast.makeText(this, "Album deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateAlbumActivity.this, MainActivity.class));
    }

}