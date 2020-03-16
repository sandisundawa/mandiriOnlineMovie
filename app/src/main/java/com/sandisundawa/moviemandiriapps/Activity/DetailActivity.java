package com.sandisundawa.moviemandiriapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.DetailMovie;
import com.sandisundawa.moviemandiriapps.Presenter.DetailMoviePresenter;
import com.sandisundawa.moviemandiriapps.R;

public class DetailActivity extends AppCompatActivity implements ViewInterface<DetailMovie> {

    private TextView judul, rating, durasi, deskripsi;
    private Button review;
    private DetailMoviePresenter detailMoviePresenter;
    private Integer idMovie;
    private static final String API_KEY = "2c35c921410c9727265ed66192629a38";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Hawk.init(this).build();
        idMovie = Hawk.get("idMovie");

        judul = findViewById(R.id.title_movie);
        rating = findViewById(R.id.rating);
        durasi = findViewById(R.id.durasi);
        deskripsi = findViewById(R.id.overview);
        review = findViewById(R.id.see_review);

        detailMoviePresenter = new DetailMoviePresenter(this);
        detailMoviePresenter.getData(idMovie, API_KEY);
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void display(DetailMovie data) {
        judul.setText(data.getOriginalTitle());
        rating.setText("Rating : "+data.getVoteAverage());
        durasi.setText(data.getRuntime()+" menit");
        deskripsi.setText(data.getOverview());

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRev = new Intent(DetailActivity.this, ReviewActivity.class);
                startActivity(toRev);
            }
        });
    }

    @Override
    public void displayError(String s) {
        Toast.makeText(this, "Error "+s, Toast.LENGTH_LONG).show();
    }
}
