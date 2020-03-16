package com.sandisundawa.moviemandiriapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.sandisundawa.moviemandiriapps.Adapter.MovieAdapter;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.Movie;
import com.sandisundawa.moviemandiriapps.Presenter.MoviePresenter;
import com.sandisundawa.moviemandiriapps.R;

public class MovieActivity extends AppCompatActivity implements ViewInterface<Movie> {

    private RecyclerView rvMovie;
    private MoviePresenter moviePresenter;
    private MovieAdapter movieAdapter;
    private Integer idGenre;
    private static final String API_KEY = "2c35c921410c9727265ed66192629a38";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Hawk.init(this).build();
        idGenre = Hawk.get("idGenre");

        String idGenreString = idGenre.toString();

        rvMovie = findViewById(R.id.rv_movie);
        moviePresenter = new MoviePresenter(this);
        moviePresenter.getData(API_KEY, idGenreString);

    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void display(Movie data) {
        movieAdapter = new MovieAdapter(data.getResults(), this);
        rvMovie.setAdapter(movieAdapter);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayError(String s) {
        Toast.makeText(this, "Error "+s, Toast.LENGTH_LONG).show();
    }
}
