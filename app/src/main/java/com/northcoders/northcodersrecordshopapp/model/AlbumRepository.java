package com.northcoders.northcodersrecordshopapp.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.northcodersrecordshopapp.service.AlbumApiService;
import com.northcoders.northcodersrecordshopapp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {

    private List<Album> albums = new ArrayList<>();
    private MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public AlbumRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Album>> getMutableLiveData() {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<List<Album>> call = albumApiService.getAllAlbums();

        call.enqueue(new Callback<List<Album>>() {

            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Album> albumList = response.body();
                    mutableLiveData.setValue(albumList);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable throwable) {
                Log.i("HTTP Failure", throwable.getMessage());

            }
        });
        return mutableLiveData;
    }

    public void addAlbum(Album album) {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<Album> call = albumApiService.addAlbum(album);

        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(application.getApplicationContext(),
                            "Album added to the database",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),
                            "Failed to add album to the database",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable throwable) {
               Toast.makeText(application.getApplicationContext(),
                       "Unable to add album to the database",
                       Toast.LENGTH_SHORT).show();
                        Log.e("POST REQ", throwable.getMessage());

            }
        });
    }
}
