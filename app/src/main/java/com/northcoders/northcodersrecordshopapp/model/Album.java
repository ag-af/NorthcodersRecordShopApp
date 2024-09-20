package com.northcoders.northcodersrecordshopapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.northcoders.northcodersrecordshopapp.BR;

public class Album extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("artist")
    private String artist;
    @SerializedName("genre")
    private Genre genre;
    @SerializedName("releaseYear")
    private Integer releaseYear;
    @SerializedName("price")
    private Double price;
    @SerializedName("stock")
    private Integer stock;
    private String imageName;

    public Album(long id, String title, String artist, Genre genre, Integer releaseYear, Double price, Integer stock) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.price = price;
        this.stock = stock;
    }

    public Album() {
    }

    protected Album(Parcel in) {
        id = in.readLong();
        title = in.readString();
        artist = in.readString();
        if (in.readByte() == 0) {
            releaseYear = null;
        } else {
            releaseYear = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        if (in.readByte() == 0) {
            stock = null;
        } else {
            stock = in.readInt();
        }
        imageName = in.readString();
        genre = Genre.valueOf(in.readString());
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public String getReleaseYear() {
        if (releaseYear == null) {
            return null;
        }
        return String.valueOf(releaseYear);
    }

    public void setReleaseYear(String releaseYear) {
        try {
            this.releaseYear = Integer.parseInt(releaseYear);
        } catch (NumberFormatException e) {
            this.releaseYear = null;
        }
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public String getPrice() {
        if(price == null || price == 0.0) {
            return "";
        }
        return String.format("%.2f", price);
    }

    public void setPrice(String price) {
        try {
            this.price = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            this.price = null;
        }
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getStock() {
        if (stock == null) {
            return null;
        }
        return String.valueOf(stock);
    }

    public void setStock(String stock) {
        try {
            this.stock = Integer.parseInt(stock);
        } catch (NumberFormatException e) {
            this.stock = null;
        }
        notifyPropertyChanged(BR.stock);
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(artist);
        if (releaseYear == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(releaseYear);
        }
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        if (stock == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(stock);
        }
        parcel.writeString(imageName);
        parcel.writeString(genre.name());
    }
}

