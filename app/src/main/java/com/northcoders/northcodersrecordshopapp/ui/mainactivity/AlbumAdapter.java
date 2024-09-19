package com.northcoders.northcodersrecordshopapp.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.northcodersrecordshopapp.R;
import com.northcoders.northcodersrecordshopapp.databinding.AlbumItemBinding;
import com.northcoders.northcodersrecordshopapp.model.Album;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albumList;
    private Context context;

    public AlbumAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final AlbumItemBinding albumItemBinding;

        public AlbumViewHolder(AlbumItemBinding albumItemBinding) {
            super(albumItemBinding.getRoot());
            this.albumItemBinding = albumItemBinding;
        }
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AlbumItemBinding itemBinding = AlbumItemBinding.inflate(layoutInflater, parent, false);
        return new AlbumViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumItemBinding.setAlbum(album);

        if (album.getTitle().equalsIgnoreCase("How Deep Is Your Love")) {
            holder.albumItemBinding.albumArtwork.setImageResource(R.drawable.how_deep_is_your_love);
        } else if (album.getTitle().equalsIgnoreCase("Don't Dream It's Over")) {
            holder.albumItemBinding.albumArtwork.setImageResource(R.drawable.dont_dream_its_over);
        } else if (album.getTitle().equalsIgnoreCase("All Out of Love")) {
            holder.albumItemBinding.albumArtwork.setImageResource(R.drawable.all_out_of_love);
        } else {
            holder.albumItemBinding.albumArtwork.setImageResource(R.drawable.album_icon);
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
