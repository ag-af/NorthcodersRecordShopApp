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

import java.util.List;

public class AlbumRepository {

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
                       "Unable to add album to database",
                       Toast.LENGTH_SHORT).show();
                        Log.e("POST REQ", throwable.getMessage());

            }
        });
    }

    public void updateAlbum(long id, Album updatedAlbum) {

        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<Album> call = albumApiService.updateAlbum(id, updatedAlbum);

        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(application.getApplicationContext(),
                        "Album updated successfully",
                        Toast.LENGTH_SHORT).show();
                }

            @Override
            public void onFailure(Call<Album> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                        "Failed to update album",
                        Toast.LENGTH_SHORT).show();

                Log.e("PUT REQUEST", throwable.getMessage());
            }
        });
    }

    public void deleteAlbum(long id) {

        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<Void> call = albumApiService.deleteAlbum(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(application.getApplicationContext(),
                        "Album deleted succesfully",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                        "Failed to delete album",
                        Toast.LENGTH_SHORT).show();

                Log.e("DELETE REQ", throwable.getMessage());
            }
        });
    }
}
