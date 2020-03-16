package com.sandisundawa.moviemandiriapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.orhanobut.hawk.Hawk;
import com.sandisundawa.moviemandiriapps.Activity.DetailActivity;
import com.sandisundawa.moviemandiriapps.Model.Result;
import com.sandisundawa.moviemandiriapps.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    //var
    List<Result> results;
    Context context;
    private static final String baseImage = "https://image.tmdb.org/t/p/w500";

    //constructor
    public MovieAdapter(List<Result> results, Context context){
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder myViewHolder, int i) {
        Result result = results.get(i);

        myViewHolder.judul.setText(result.getOriginalTitle());
        myViewHolder.tanggal.setText(result.getReleaseDate());
        myViewHolder.rating.setText("Rating :"+result.getVoteAverage().toString());

        if(result.getPosterPath()!=null){
            RequestOptions requestOptions = new RequestOptions();
            Glide.with(context).load(baseImage+result.getPosterPath()).
                    apply(requestOptions).into(myViewHolder.imageView);
        }

        myViewHolder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.init(context).build();
                Hawk.put("idMovie",result.getId());
                Intent toDetail = new Intent(context, DetailActivity.class);
                context.startActivity(toDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView judul, tanggal, rating;
        CardView cvMovie;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.title_movie);
            tanggal = itemView.findViewById(R.id.release);
            rating = itemView.findViewById(R.id.rating);
            cvMovie = itemView.findViewById(R.id.cv_movie);
            imageView = itemView.findViewById(R.id.image_movie);

        }
    }
}