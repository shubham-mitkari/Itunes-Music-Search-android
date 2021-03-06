package com.example.sampleitunessearch.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Songs {
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    @SerializedName("imageUrl")
    @Expose
    private static String imageUrl;

    public Songs(String trackName, String artistName, String previewUrl) {

        this.trackName = trackName;
        this.artistName = artistName;
        this.previewUrl = previewUrl;

    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}

