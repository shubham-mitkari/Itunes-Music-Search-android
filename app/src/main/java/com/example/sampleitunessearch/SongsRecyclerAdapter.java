package com.example.sampleitunessearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.ViewHolder> {

    List<Songs> songsList ;

    SongsRecyclerAdapter(List<Songs> songsList) {
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.song_card_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Songs songs = this.songsList.get(position);
        Glide.with(holder.musicImage.getContext())
                .load(Songs.getImageUrl())
                .into(holder.musicImage);

        holder.trackName.setText(songs.getTrackName());
        holder.artistName.setText(songs.getArtistName());
    }

    @Override
    public int getItemCount() {
        return this.songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView musicImage;
        public TextView trackName, artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            musicImage = itemView.findViewById(R.id.music_image);
            trackName = itemView.findViewById(R.id.track_name);
            artistName = itemView.findViewById(R.id.artist_name);
        }
    }
}
