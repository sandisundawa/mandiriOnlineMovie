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
import com.sandisundawa.moviemandiriapps.Model.ResultReview;
import com.sandisundawa.moviemandiriapps.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>{

    //var
    List<ResultReview> resultReviews;
    Context context;

    //constructor
    public ReviewAdapter(List<ResultReview> resultReviews, Context context){
        this.resultReviews = resultReviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_review, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder myViewHolder, int i) {
        ResultReview resultReview = resultReviews.get(i);

        myViewHolder.nama.setText(resultReview.getAuthor());
        myViewHolder.komen.setText(resultReview.getContent());

    }

    @Override
    public int getItemCount() {
        return resultReviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama, komen;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama_orang);
            komen = itemView.findViewById(R.id.isi_komen);


        }
    }
}
