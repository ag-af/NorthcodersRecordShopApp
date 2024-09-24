package com.northcoders.northcodersrecordshopapp.ui.mainactivity;

import android.content.Intent;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;


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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        searchView = findViewById(R.id.searchView);

        recyclerView = findViewById(R.id.albumRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        albumAdapter = new AlbumAdapter(albums, this, this);
        recyclerView.setAdapter(albumAdapter);

        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        MainActivityClickHandler clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

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

        if (query.isEmpty()) {
            albumAdapter.updateAlbumList(albums);
            return;
        }

        List<Album> filteredAlbums = new ArrayList<>();

        if(!query.isEmpty()) {

            for (Album album : albums) {
                if (album.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        album.getArtist().toLowerCase().contains(query.toLowerCase()) ||
                        String.valueOf(album.getReleaseYear()).contains(query)) {

                    filteredAlbums.add(album);
                }
            }
        }

        if (filteredAlbums.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
            albumAdapter.updateAlbumList(filteredAlbums);
        }

    private void getAllAlbums() {
        viewModel.getAlbumList().observe(this, new Observer<List<Album>>() {

            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                if (albumsFromLiveData != null) {
                    albums.clear();
                    albums.addAll(albumsFromLiveData);

                    Collections.sort(albums, new Comparator<Album>() {
                        @Override
                        public int compare(Album album1, Album album2) {
                            return album1.getTitle().compareToIgnoreCase(album2.getTitle());
                        }
                    });

                    if (albumAdapter == null) {
                        albumAdapter = new AlbumAdapter(albums, MainActivity.this, MainActivity.this);
                        recyclerView.setAdapter(albumAdapter);
                    } else {
                    albumAdapter.notifyDataSetChanged();
                }
            }
        }
        });
    }


    public void onItemClick(int position) {

        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);

        Album selectedAlbum = albums.get(position);

        intent.putExtra("selected_album", selectedAlbum);
        startActivity(intent);
    }

    @Override
    public void onItemDelete(Long albumId) {
        viewModel.deleteAlbum(albumId);
        Toast.makeText(this, "Album deleted successfully", Toast.LENGTH_SHORT).show();
    }
}