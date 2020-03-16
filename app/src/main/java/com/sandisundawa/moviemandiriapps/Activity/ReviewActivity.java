package com.sandisundawa.moviemandiriapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.sandisundawa.moviemandiriapps.Adapter.ReviewAdapter;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.Review;
import com.sandisundawa.moviemandiriapps.Presenter.ReviewPresenter;
import com.sandisundawa.moviemandiriapps.R;

public class ReviewActivity extends AppCompatActivity implements ViewInterface<Review> {

    private RecyclerView rvReview;
    private Integer movieId;
    private TextView empty;
    private ReviewPresenter reviewPresenter;
    private ReviewAdapter reviewAdapter;
    private static final String API_KEY = "2c35c921410c9727265ed66192629a38";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Hawk.init(this).build();
        movieId = Hawk.get("idMovie");
        rvReview = findViewById(R.id.rv_review);
        empty = findViewById(R.id.empty);

        reviewPresenter = new ReviewPresenter(this);
        reviewPresenter.getData(movieId, API_KEY);
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void display(Review data) {

        if (data.getResults().size()==0){
            empty.setVisibility(View.VISIBLE);
        }

        reviewAdapter = new ReviewAdapter(data.getResults(), this);
        rvReview.setAdapter(reviewAdapter);
        rvReview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayError(String s) {
        Toast.makeText(this, "Error "+s, Toast.LENGTH_LONG).show();

    }
}
