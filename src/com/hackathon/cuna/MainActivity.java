package com.hackathon.cuna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.hackathon.cuna.fragments.CalendarFragment;
import com.hackathon.cuna.fragments.ChartFragment;
import com.hackathon.cuna.fragments.InterviewFragment;
import com.hackathon.cuna.position.Candidate;
import com.hackathon.cuna.position.Position;
import com.hackathon.kuna.R;
import com.hackathon.kuna.SplashActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends SherlockFragmentActivity implements
		TabListener, OnItemClickListener {

	public static List<Candidate> allCandidates;
	public static List<Position> allPositions;
	private SlidingMenu slidingMenu;
	private ListView myListView;
	private Tab[] tabArr;
	private static Map<String, SherlockFragment> fragmentMap;
	static {
		fragmentMap = new HashMap<String, SherlockFragment>();
		fragmentMap.put("Interviews", new InterviewFragment(
				R.layout.fragment_interviews));
		fragmentMap.put("Calendar", new CalendarFragment(
				R.layout.fragment_calendar));
		fragmentMap.put("Charts", new ChartFragment(R.layout.fragment_charts));

		allCandidates = new ArrayList<Candidate>();
		allPositions = new ArrayList<Position>();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTabNavigation();
		addTabsToActionBar();

		startActivity(new Intent(this, SplashActivity.class));
		instantiateSlidingMenu();
		setSlidingMenuDefaults();
		setSlidingMenuToListView();

	}

	public void instantiateSlidingMenu() {
		slidingMenu = new SlidingMenu(this);
		
	}

	public void setSlidingMenuDefaults() {
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
	}

	public void setSlidingMenuToListView() {
		myListView = new ListView(this);
		myListView.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_checked, new String[] {
						"INTERVIEWS", "CALENDAR", "CHARTS" }));
		slidingMenu.setMenu(myListView);
		myListView.setOnItemClickListener(this);
	}

	public void setTabNavigation() {
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	public void addTabsToActionBar() {
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText("Interviews")
						.setTabListener(this));
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText("Calendar")
						.setTabListener(this));
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText("Charts")
						.setTabListener(this));
		getSupportActionBar().selectTab(getSupportActionBar().getTabAt(0));

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		SherlockFragment fragment = fragmentMap.get(tab.getText());
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case 0:
			getSupportActionBar().selectTab(getSupportActionBar().getTabAt(0));

			break;
		case 1:
			getSupportActionBar().selectTab(getSupportActionBar().getTabAt(1));
			break;
		case 2:
			getSupportActionBar().selectTab(getSupportActionBar().getTabAt(2));

			break;
		}
	}

}
