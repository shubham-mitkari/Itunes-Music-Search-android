package com.example.sampleitunessearch.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResposnsePojo {

    @SerializedName("resultCount")
    @Expose
    private int resultCount;
    @SerializedName("results")
    @Expose
    private List<Songs> songsList = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Songs> getSongsList() {
        return songsList;
    }

    public void setSongsList(List<Songs> songsList) {
        this.songsList = songsList;
    }
}
