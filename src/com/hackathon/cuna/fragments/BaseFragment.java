package com.hackathon.cuna.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.hackathon.cuna.FragmentInterface;

@SuppressLint("ValidFragment")
public class BaseFragment extends SherlockFragment implements FragmentInterface {
	protected int resource;
	protected View viewInflated;

	public BaseFragment() {
		// TODO Auto-generated constructor stub
	}

	public BaseFragment(int resourceToInflate) {
		super();
		this.resource = resourceToInflate;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		viewInflated = inflater.inflate(resource, container, false);
		onCreate();
		return viewInflated;
	}

	@Override
	public void onCreate() {
		initComp();
		findViewsById();
		setupUi();
	}
/*
 * SHOULD THIS BE ABSTRACT CLASS?
 * 
 * 
 * (non-Javadoc)
 * @see com.hackathon.cuna.FragmentInterface#findViewsById()
 */
	@Override
	public void findViewsById() {

	}

	@Override
	public void initComp() {

	}

	@Override
	public void setupUi() {
		
	}
	

}
