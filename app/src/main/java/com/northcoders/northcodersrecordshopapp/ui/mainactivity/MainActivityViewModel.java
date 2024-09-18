package com.northcoders.northcodersrecordshopapp.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.northcodersrecordshopapp.model.Album;
import com.northcoders.northcodersrecordshopapp.model.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.albumRepository = new AlbumRepository(application);
    }

    public LiveData<List<Album>> getAlbumList() {
        return albumRepository.getMutableLiveData();
    }
}
