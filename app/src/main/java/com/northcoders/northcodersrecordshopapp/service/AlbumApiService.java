package com.northcoders.northcodersrecordshopapp.service;

import com.northcoders.northcodersrecordshopapp.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumApiService {
    @GET("albums")
    Call<List<Album>> getAllAlbums();

    @POST("albums")
    Call<Album> addAlbum(@Body Album album);

    @PUT("albums/{id}")
    Call<Album> updateAlbum(@Path("id") long id, @Body Album album);

    @DELETE("albums/{id}")
    Call<Void> deleteAlbum(@Path("id") long id);
}
