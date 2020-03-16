package com.sandisundawa.moviemandiriapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.sandisundawa.moviemandiriapps.Activity.MovieActivity;
import com.sandisundawa.moviemandiriapps.Model.Genre;
import com.sandisundawa.moviemandiriapps.R;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder>{

    //var
    List<Genre> genres;
    Context context;

    //constructor
    public GenreAdapter(List<Genre> genres, Context context){
        this.genres = genres;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_genre, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Genre genre = genres.get(i);

        myViewHolder.namaGenre.setText(genre.getName());
        myViewHolder.cvGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.init(context).build();
                Hawk.put("idGenre", genre.getId());
                Intent toMovie = new Intent(context, MovieActivity.class);
                context.startActivity(toMovie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namaGenre;
        CardView cvGenre;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            namaGenre = itemView.findViewById(R.id.nama_genre);
            cvGenre = itemView.findViewById(R.id.cv_genre);
        }
    }
}
