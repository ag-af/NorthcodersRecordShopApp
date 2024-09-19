package com.northcoders.northcodersrecordshopapp.ui.mainactivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.northcodersrecordshopapp.ui.addalbum.AddNewAlbumActivity;

public class MainActivityClickHandler {

    private Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onAddNewAlbumClick(View view) {
        Intent intent = new Intent(context, AddNewAlbumActivity.class);
        context.startActivity(intent);
    }
}
