package com.sandisundawa.moviemandiriapps.Presenter;

import com.sandisundawa.moviemandiriapps.API.ApiClient;
import com.sandisundawa.moviemandiriapps.API.ApiInterface;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.DetailMovie;
import com.sandisundawa.moviemandiriapps.Model.Movie;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailMoviePresenter {
    ViewInterface<DetailMovie> viewInterface;
    private Observable<DetailMovie> subscribe;

    public DetailMoviePresenter(ViewInterface<DetailMovie> viewInterface) {
        this.viewInterface = viewInterface;
    }

    public Observable<DetailMovie> getObservable(Integer movieId, String apiKey) {
        return ApiClient.getApiClient().create(ApiInterface.class).getDetail(movieId, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getData(Integer movieId, String apiKey) {
        subscribe = getObservable(movieId, apiKey);
        subscribe.subscribeWith(getObserver());
    }

    public DisposableObserver<DetailMovie> getObserver() {
        return new DisposableObserver<DetailMovie>() {

            @Override
            public void onNext(DetailMovie ResponseDataList) {
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
