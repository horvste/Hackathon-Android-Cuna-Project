package com.hackathon.kuna;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class CanidateDisplay extends Activity {
	private TextView showCanTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canidate_display);
		showCanTextView = (TextView) findViewById(R.id.textView11);

		String push = (String) getIntent().getExtras().get("push");
		if (push != null)
			showCanTextView.setText(push);
		else
			showCanTextView.setText("Something Went Wrong!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canidate_display, menu);
		return true;
	}

}
