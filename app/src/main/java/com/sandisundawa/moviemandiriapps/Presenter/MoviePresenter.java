package com.sandisundawa.moviemandiriapps.Presenter;

import com.sandisundawa.moviemandiriapps.API.ApiClient;
import com.sandisundawa.moviemandiriapps.API.ApiInterface;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.Movie;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter {
    ViewInterface<Movie> viewInterface;
    private Observable<Movie> subscribe;

    public MoviePresenter(ViewInterface<Movie> viewInterface) {
        this.viewInterface = viewInterface;
    }

    public Observable<Movie> getObservable(String apiKey, String withGenres) {
        return ApiClient.getApiClient().create(ApiInterface.class).getMovie(apiKey, withGenres)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getData(String apiKey, String withGenres) {
        subscribe = getObservable(apiKey, withGenres);
        subscribe.subscribeWith(getObserver());
    }

    public DisposableObserver<Movie> getObserver() {
        return new DisposableObserver<Movie>() {

            @Override
            public void onNext(Movie ResponseDataList) {
                viewInterface.display(ResponseDataList);

            }

            @Override
            public void onError(Throwable e) {
                viewInterface.displayError(e.getMessage());

            }


            @Override
            public void onComplete() {

            }
        };
    }

    public void cancel() {
        subscribe.unsubscribeOn(Schedulers.io());
    }
}
