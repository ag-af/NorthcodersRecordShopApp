package com.northcoders.northcodersrecordshopapp.service;

import com.northcoders.northcodersrecordshopapp.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlbumApiService {
    @GET("albums")
    Call<List<Album>> getAllAlbums();

    @POST("albums")
    Call<Album> createAlbum(@Body Album album);
}
