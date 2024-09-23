package com.northcoders.northcodersrecordshopapp.ui.mainactivity;

import android.content.Intent;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.northcodersrecordshopapp.R;
import com.northcoders.northcodersrecordshopapp.databinding.ActivityMainBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<Album> albums = new ArrayList<>();
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        MainActivityClickHandler clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

        recyclerView = binding.albumRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


            albumAdapter = new AlbumAdapter(albums, this, this);
                recyclerView.setAdapter(albumAdapter);

        getAllAlbums();
    }

    private void getAllAlbums() {
        viewModel.getAlbumList().observe(this, new Observer<List<Album>>() {

            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                if (albumsFromLiveData != null) {
                    albums.clear();
                    albums.addAll(albumsFromLiveData);
                    albumAdapter.notifyDataSetChanged();
                }
            }
        });
    }

//    private void displayInRecyclerView() {
//
//        recyclerView = binding.albumRecyclerView;
//        albumAdapter = new AlbumAdapter(albums, this, this);
//        recyclerView.setAdapter(albumAdapter);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//        albumAdapter.notifyDataSetChanged();
//    }

    public void onItemClick(int position) {
        Album selectedAlbum = albums.get(position);
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);
        intent.putExtra("selected_album", selectedAlbum);
        startActivity(intent);
    }
}