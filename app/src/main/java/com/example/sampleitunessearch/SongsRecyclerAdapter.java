package com.example.sampleitunessearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sampleitunessearch.Model.Songs;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.ViewHolder> {

    Context context;
    List<Songs> songsList;
    private SongsAdapterListener listener;

    SongsRecyclerAdapter(Context context, List<Songs> songsList, SongsAdapterListener listener) {
        this.context = context;
        this.songsList = songsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.song_card_view, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Songs songs = songsList.get(position);
        Glide.with(holder.musicImage.getContext())
                .load(Songs.getImageUrl())
                .into(holder.musicImage);

        holder.trackName.setText(songs.getTrackName());
        holder.artistName.setText(songs.getArtistName());
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView musicImage;
        public TextView trackName, artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            musicImage = itemView.findViewById(R.id.music_image);
            trackName = itemView.findViewById(R.id.track_name);
            artistName = itemView.findViewById(R.id.artist_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSongSelected(songsList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface SongsAdapterListener {
        void onSongSelected(Songs songs);
    }
}
