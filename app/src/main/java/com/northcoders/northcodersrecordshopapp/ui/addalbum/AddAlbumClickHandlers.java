package com.northcoders.northcodersrecordshopapp.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivity;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {

    private Album album;
    private Context context;
    private MainActivityViewModel mainActivityViewModel;

    public AddAlbumClickHandlers(Context context, MainActivityViewModel mainActivityViewModel) {
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
        this.album = new Album();
    }

    public void onSubmitButtonClicked(View view) {
        if(album.getTitle() == null || album.getTitle().isEmpty() ||
        album.getArtist() == null || album.getArtist().isEmpty() ||
        album.getReleaseYearString() == null || album.getReleaseYearString().isEmpty() ||
        album.getPriceString() == null || album.getPriceString().isEmpty() ||
        album.getStockString() == null || album.getStockString().isEmpty()) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {

            Album newAlbum = new Album(
            album.getId(),
            album.getTitle(),
            album.getArtist(),
            album.getGenre(),
            Integer.parseInt(album.getReleaseYearString()),
            Double.parseDouble(album.getPriceString()),
            Integer.parseInt(album.getStockString())
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
