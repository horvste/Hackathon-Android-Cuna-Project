package com.hackathon.cuna.fragments;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.hackathon.kuna.R;

@SuppressLint("ValidFragment")
public class ChartFragment extends BaseFragment {
	private ImageView imageView;

	public ChartFragment() {
		super();
	}

	public ChartFragment(int resource) {
		super(resource);
	}

	@Override
	public void findViewsById() {
		imageView = (ImageView) viewInflated.findViewById(R.id.imageView1);
		imageView.setImageResource(R.drawable.chart);
		

	}

	@Override
	public void initComp() {

	}

	@Override
	public void setupUi() {

	}
}
