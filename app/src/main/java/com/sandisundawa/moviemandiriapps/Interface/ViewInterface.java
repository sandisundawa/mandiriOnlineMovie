package com.sandisundawa.moviemandiriapps.Interface;

public interface ViewInterface<T> {
    void showToast(String s);

    void display(T data);

    void displayError(String s);
}