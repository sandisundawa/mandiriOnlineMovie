package com.sandisundawa.moviemandiriapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sandisundawa.moviemandiriapps.Adapter.GenreAdapter;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.MainGenre;
import com.sandisundawa.moviemandiriapps.Presenter.GenrePresenter;
import com.sandisundawa.moviemandiriapps.R;

public class MainActivity extends AppCompatActivity implements ViewInterface<MainGenre> {

    private RecyclerView rvGenre;
    private GenrePresenter genrePresenter;
    private GenreAdapter genreAdapter;
    private static final String API_KEY = "2c35c921410c9727265ed66192629a38";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGenre = findViewById(R.id.rv_genre);

        genrePresenter = new GenrePresenter(this);
        genrePresenter.getData(API_KEY);
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void display(MainGenre data) {
        genreAdapter = new GenreAdapter(data.getGenres(), this);
        rvGenre.setAdapter(genreAdapter);
        rvGenre.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayError(String s) {
        Toast.makeText(this, "Error "+s, Toast.LENGTH_LONG).show();
    }
}
