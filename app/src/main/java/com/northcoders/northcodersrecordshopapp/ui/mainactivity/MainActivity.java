package com.northcoders.northcodersrecordshopapp.ui.mainactivity;

import android.content.Intent;

import android.os.Bundle;
import android.widget.SearchView;


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
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.albumRecyclerView);

        //        recyclerView = binding.albumRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        MainActivityClickHandler clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

            albumAdapter = new AlbumAdapter(albums, this, this);
                recyclerView.setAdapter(albumAdapter);

        getAllAlbums();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterAlbums(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterAlbums(newText);
                return true;
            }
        });

    }

    public void filterAlbums(String query) {
        List<Album> filteredAlbums = new ArrayList<>();

        for (Album album : albums) {
            if (album.getTitle().toLowerCase().contains(query.toLowerCase()) ||
            album.getArtist().toLowerCase().contains(query.toLowerCase())) {
                filteredAlbums.add(album);
            }
        }
        if (albumAdapter != null) {
            albumAdapter.updateAlbumList(filteredAlbums);
        }
    }

    private void getAllAlbums() {
        viewModel.getAlbumList().observe(this, new Observer<List<Album>>() {

            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                if (albumsFromLiveData != null) {
                    albums.clear();
                    albums.addAll(albumsFromLiveData);
                }
                    if (albumAdapter == null) {
                        albumAdapter = new AlbumAdapter(albums, MainActivity.this, MainActivity.this);
                        recyclerView.setAdapter(albumAdapter);
                    } else {
                    albumAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    public void onItemClick(int position) {
        Album selectedAlbum = albums.get(position);
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);
        intent.putExtra("selected_album", selectedAlbum);
        startActivity(intent);
    }
}