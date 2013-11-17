package com.hackathon.kuna;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class SplashActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				ImageView imageView = new ImageView(getBaseContext());
				imageView.setScaleType(ScaleType.FIT_XY);

				imageView.setImageResource(R.drawable.ic_launcher);
				setContentView(imageView);

			}

		}, 1000);
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				ImageView imageView = new ImageView(getBaseContext());
				imageView.setScaleType(ScaleType.FIT_XY);

				imageView.setImageResource(R.drawable.ic_launcher);
				setContentView(imageView);

			}

		}, 0);
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				ImageView imageView = new ImageView(getBaseContext());
				imageView.setScaleType(ScaleType.FIT_XY);
				imageView.setImageResource(R.drawable.cuna_splash);
				setContentView(imageView);

			}

		}, 2000);
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				finish();
				//
				//

			}

		}, 3500);

	}


}
