package com.northcoders.northcodersrecordshopapp.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.northcoders.northcodersrecordshopapp.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.model.Genre;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivity;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumClickHandlers {

    private Album album;
    private MainActivityViewModel viewModel;
    private Context context;
    private Spinner genreSpinner;
    private ActivityUpdateAlbumBinding binding;

    public UpdateAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel, Spinner genreSpinner, ActivityUpdateAlbumBinding binding) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
        this.genreSpinner = genreSpinner;
        this.binding = binding;
    }

    public void onUpdateButtonClicked(View view) {
        album.setTitle(binding.editTitle.getText().toString());
        album.setArtist(binding.editArtist.getText().toString());
        album.setReleaseYear(binding.editReleaseYear.getText().toString());
        album.setStock(binding.editStock.getText().toString());
        String genreString = (String) binding.spinnerGenre.getSelectedItem();
        album.setGenre(Genre.valueOf(genreString));

        String price = binding.editPrice.getText().toString();
        try {
            album.setPrice(price);
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.updateAlbum(album.getId(), album);

        Toast.makeText(context, "Album updated successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onDeleteButtonClicked(View view) {
        viewModel.deleteAlbum(album.getId());
        Toast.makeText(context, "Album deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
