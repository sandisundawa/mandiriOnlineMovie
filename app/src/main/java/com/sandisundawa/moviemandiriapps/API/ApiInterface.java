package com.sandisundawa.moviemandiriapps.API;

import android.content.Intent;

import com.sandisundawa.moviemandiriapps.Model.DetailMovie;
import com.sandisundawa.moviemandiriapps.Model.MainGenre;
import com.sandisundawa.moviemandiriapps.Model.Movie;
import com.sandisundawa.moviemandiriapps.Model.Review;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //genre
    @GET("genre/movie/list")
    Observable<MainGenre> getGenre (@Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<Movie> getMovie (@Query("api_key") String apiKey,
                                @Query("with_genres") String withGenres);

    @GET("movie/{movie_id}")
    Observable<DetailMovie> getDetail (@Path("movie_id") Integer movieId,
                                       @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Observable<Review> getReview (@Path("movie_id")Integer movieId,
                                  @Query("api_key") String apiKey);
}
