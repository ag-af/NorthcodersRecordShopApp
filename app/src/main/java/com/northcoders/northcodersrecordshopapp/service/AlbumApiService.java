package com.northcoders.northcodersrecordshopapp.service;

import com.northcoders.northcodersrecordshopapp.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApiService {
    @GET("albums")
    Call<List<Album>> getAllAlbums();
}
