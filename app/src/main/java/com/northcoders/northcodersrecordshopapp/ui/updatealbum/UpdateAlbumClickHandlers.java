package com.northcoders.northcodersrecordshopapp.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.model.Genre;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivity;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumClickHandlers {

    private Album album;
    private MainActivityViewModel viewModel;
    private Context context;
    private Spinner genreSpinner;

    public UpdateAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel, Spinner genreSpinner) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
        this.genreSpinner = genreSpinner;
    }

    public void onUpdateButtonClicked(View view) {
        Album updatedAlbum = new Album();
        updatedAlbum.setId(album.getId());
        updatedAlbum.setTitle(album.getTitle());
        updatedAlbum.setArtist(album.getArtist());

        String selectedGenre = genreSpinner.getSelectedItem().toString();
        if (selectedGenre.equalsIgnoreCase("Select a genre")) {
            Toast.makeText(context, "Please select a genre", Toast.LENGTH_SHORT).show();
            return;
        } else {
            updatedAlbum.setGenre(Genre.valueOf(selectedGenre.toUpperCase()));
        }

        updatedAlbum.setReleaseYear(album.getReleaseYear());
        updatedAlbum.setPrice(album.getPrice());
        updatedAlbum.setStock(album.getStock());

        if (updatedAlbum.getTitle() == null || updatedAlbum.getTitle().isEmpty() ||
                updatedAlbum.getArtist() == null || updatedAlbum.getArtist().isEmpty() ||
                updatedAlbum.getReleaseYear() == null || updatedAlbum.getReleaseYear().isEmpty() ||
                updatedAlbum.getPrice() == null || updatedAlbum.getPrice().isEmpty() ||
                updatedAlbum.getStock() == null || updatedAlbum.getStock().isEmpty()) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.updateAlbum(album.getId(), updatedAlbum);

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}
