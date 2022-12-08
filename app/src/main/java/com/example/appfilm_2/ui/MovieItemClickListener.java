package com.example.appfilm_2.ui;

import android.widget.ImageView;

import com.example.appfilm_2.model.FilmModel;

public interface MovieItemClickListener {
void onMovieClick(FilmModel filmModel, ImageView movieImageView);
}
