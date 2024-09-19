package com.northcoders.northcodersrecordshopapp.ui.addalbum;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.northcodersrecordshopapp.R;
import com.northcoders.northcodersrecordshopapp.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.ui.mainactivity.MainActivityViewModel;

public class AddNewAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandlers handlers;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        album = new Album();

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_new_album);

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handlers = new AddAlbumClickHandlers(this, viewModel);

        binding.setAlbum(album);
        binding.setClickHandler(handlers);
    }
}