package com.northcoders.northcodersrecordshopapp.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.northcoders.northcodersrecordshopapp.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.model.Genre;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivity;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {

    private Album album;
    private Context context;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityAddNewAlbumBinding binding;

    public AddAlbumClickHandlers(Context context, MainActivityViewModel mainActivityViewModel, ActivityAddNewAlbumBinding binding) {
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
        this.album = new Album();
        this.binding = binding;
    }

    public void onSubmitButtonClicked(View view) {
        String genre = binding.spinnerGenre.getSelectedItem().toString();
        album.setGenre(Genre.valueOf(genre.toUpperCase()));

        String title = binding.editTitle.getText().toString();
        String artist = binding.editArtist.getText().toString();
        String releaseYear = binding.editReleaseYear.getText().toString();
        String price = binding.editPrice.getText().toString();
        String stock = binding.editStock.getText().toString();

        String formattedPrice = String.format("%.2f", Double.parseDouble(price));
        album.setPrice(formattedPrice);

        album.setTitle(title);
        album.setArtist(artist);
        album.setReleaseYear(releaseYear);
        album.setStock(stock);

        if (title == null || title.isEmpty() ||
                artist == null || artist.isEmpty() ||
                genre == null || genre.isEmpty() ||
                releaseYear == null || releaseYear.isEmpty() ||
                price == null || price.isEmpty() ||
                stock == null || stock.isEmpty()) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

        } else {
                Album newAlbum = new Album(
                        album.getId(),
                        album.getTitle(),
                        album.getArtist(),
                        album.getGenre(),
                        Integer.parseInt(album.getReleaseYear()),
                        Double.parseDouble(album.getPrice()),
                        Integer.parseInt(album.getStock())
                );

                mainActivityViewModel.addNewAlbum(newAlbum);

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);
    }
}
