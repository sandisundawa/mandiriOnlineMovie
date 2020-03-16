package com.sandisundawa.moviemandiriapps.Presenter;

import com.sandisundawa.moviemandiriapps.API.ApiClient;
import com.sandisundawa.moviemandiriapps.API.ApiInterface;
import com.sandisundawa.moviemandiriapps.Interface.ViewInterface;
import com.sandisundawa.moviemandiriapps.Model.MainGenre;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GenrePresenter {
    ViewInterface<MainGenre> viewInterface;
    private Observable<MainGenre> subscribe;

    public GenrePresenter(ViewInterface<MainGenre> viewInterface) {
        this.viewInterface = viewInterface;
    }

    public Observable<MainGenre> getObservable(String apiKey) {
        return ApiClient.getApiClient().create(ApiInterface.class).getGenre(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getData(String apiKey) {
        subscribe = getObservable(apiKey);
        subscribe.subscribeWith(getObserver());
    }

    public DisposableObserver<MainGenre> getObserver() {
        return new DisposableObserver<MainGenre>() {

            @Override
            public void onNext(MainGenre ResponseDataList) {
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
