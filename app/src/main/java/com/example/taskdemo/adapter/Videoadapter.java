package com.example.taskdemo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.taskdemo.R;
import com.example.taskdemo.VideoPlaybackActivity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Videoadapter extends RecyclerView.Adapter<Videoadapter.MyViewHolder> {

    private List<JsonObject> datalist;

    public Videoadapter(List<JsonObject> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new MyViewHolder(itemview);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        JsonObject itemData = datalist.get(position);

        int currentPosition = holder.getAdapterPosition();
         TextView tvartist = holder.itemView.findViewById(R.id.artist_name);
         TextView tvcollection = holder.itemView.findViewById(R.id.collection_name);
         TextView tvtrack = holder.itemView.findViewById(R.id.track_name);
         TextView tvsensor = holder.itemView.findViewById(R.id.sensor_name);
         ImageView img = holder.itemView.findViewById(R.id.my_image_view);
         ImageView imgvideo = holder.itemView.findViewById(R.id.img_video);


        if (itemData != null) {
            if (itemData.has("artistName")) {
                JsonElement fieldElement = itemData.get("artistName");

                if (!fieldElement.isJsonNull()) {
                    String fieldValue = fieldElement.getAsString();
                    tvartist.setText(fieldValue);
                } else {
                    tvartist.setText("");
                }
            } else {
                tvartist.setText("");
            }


            if (itemData.has("collectionName")) {
                JsonElement fieldElement = itemData.get("collectionName");

                if (!fieldElement.isJsonNull()) {
                    String fieldValue = fieldElement.getAsString();
                    tvcollection.setText(fieldValue);
                } else {
                    tvcollection.setText("");
                }
            } else {
                tvcollection.setText("");
            }

            if (itemData.has("trackName")) {
                JsonElement fieldElement = itemData.get("trackName");

                if (!fieldElement.isJsonNull()) {
                    String fieldValue = fieldElement.getAsString();
                    tvtrack.setText(fieldValue);
                } else {
                    tvtrack.setText("");
                }
            } else {
                tvtrack.setText("");
            }
            if (itemData.has("trackCensoredName")) {
                JsonElement fieldElement = itemData.get("trackCensoredName");

                if (!fieldElement.isJsonNull()) {
                    String fieldValue = fieldElement.getAsString();
                    tvsensor.setText(fieldValue);
                } else {
                    tvsensor.setText("");
                }
            } else {
                tvsensor.setText("");
            }
            if (itemData.has("artworkUrl30")) {
                JsonElement fieldElement = itemData.get("artworkUrl30");
                String imageUrl = itemData.has("artworkUrl30") ? itemData.get("artworkUrl30").getAsString() : "";

                if (!fieldElement.isJsonNull()) {
                    Picasso.get()
                            .load(imageUrl)
                            .placeholder(R.drawable.img)
                            .error(R.drawable.img)
                            .into(img);
                } else {
                    Picasso.get()
                            .load(R.drawable.img)
                            .placeholder(R.drawable.img)
                            .error(R.drawable.img)
                            .into(img);
                }
            } else {
                Picasso.get()
                        .load(R.drawable.img)
                        .placeholder(R.drawable.img)
                        .error(R.drawable.img)
                        .into(img);
            }
            imgvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JsonObject itemData = datalist.get(currentPosition);
                    String videoUrl = itemData.has("previewUrl") ? itemData.get("previewUrl").getAsString() : "";

                    if (!videoUrl.isEmpty()) {
                        Intent intent = new Intent(holder.itemView.getContext(), VideoPlaybackActivity.class);
                        intent.putExtra("video_url", videoUrl);
                        holder.itemView.getContext().startActivity(intent);
                    } else {

                        Toast.makeText(holder.itemView.getContext(), "Video URL not available", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}

