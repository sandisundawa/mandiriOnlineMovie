package com.sandisundawa.moviemandiriapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.sandisundawa.moviemandiriapps.Adapter.MovieAdapter;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.Movie;
import com.sandisundawa.moviemandiriapps.Presenter.MoviePresenter;
import com.sandisundawa.moviemandiriapps.R;

public class MovieActivity extends AppCompatActivity implements ViewInterface<Movie> {

    private RecyclerView rvMovie;
    private TextView tvEmpty;
    private MoviePresenter moviePresenter;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        String genre = getIntent().getStringExtra("idGenre");

        rvMovie = findViewById(R.id.rv_movie);
        tvEmpty = findViewById(R.id.tv_empty);
        moviePresenter = new MoviePresenter(this);
        moviePresenter.getData(getString(R.string.api_key), genre);

    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void display(Movie data) {
        if (data == null) {
            tvEmpty.setVisibility(View.VISIBLE);
        }
        movieAdapter = new MovieAdapter(data.getResults(), this);
        rvMovie.setAdapter(movieAdapter);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayError(String s) {
        Toast.makeText(this, "Error "+s, Toast.LENGTH_LONG).show();
    }
}
