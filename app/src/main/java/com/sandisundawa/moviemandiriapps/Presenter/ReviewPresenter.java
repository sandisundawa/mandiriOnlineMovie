package com.sandisundawa.moviemandiriapps.Presenter;

import com.sandisundawa.moviemandiriapps.API.ApiClient;
import com.sandisundawa.moviemandiriapps.API.ApiInterface;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.Movie;
import com.sandisundawa.moviemandiriapps.Model.Review;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ReviewPresenter {
    ViewInterface<Review> viewInterface;
    private Observable<Review> subscribe;

    public ReviewPresenter(ViewInterface<Review> viewInterface) {
        this.viewInterface = viewInterface;
    }

    public Observable<Review> getObservable(Integer movieId, String apiKey) {
        return ApiClient.getApiClient().create(ApiInterface.class).getReview(movieId, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getData(Integer movieId, String apiKey) {
        subscribe = getObservable(movieId, apiKey);
        subscribe.subscribeWith(getObserver());
    }

    public DisposableObserver<Review> getObserver() {
        return new DisposableObserver<Review>() {

            @Override
            public void onNext(Review ResponseDataList) {
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
