package com.hackathon.cuna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.hackathon.cuna.customviews.FormView;
import com.hackathon.cuna.position.Candidate;
import com.hackathon.cuna.position.Position;
import com.hackathon.kuna.CreatePositionActivity;
import com.hackathon.kuna.R;

public class CreateInterviewerActivity extends SherlockActivity implements
		OnClickListener {
	/**
	 * 0 = fName; 1 = mName; 2 = lName;
	 * 
	 */
	protected FormView[] mFormArr;
	protected Button submitButton;
	protected Button createPositionButton;
	protected ArrayAdapter mSpinnerAdapter;
	protected Spinner mSpinner;
	protected TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interview);
		findViewsById();
		setUiDefaults();
	}

	public void findViewsById() {
		mFormArr = new FormView[3];
		mFormArr[0] = (FormView) findViewById(R.id.formView1);
		mFormArr[1] = (FormView) findViewById(R.id.formView2);
		mFormArr[2] = (FormView) findViewById(R.id.formView3);
		textView = (TextView) findViewById(R.id.textView10);
		textView.setText("Position =");
		mSpinner = (Spinner) findViewById(R.id.spinner1);
		mSpinner.setAdapter(new ArrayAdapter(getBaseContext(),
				android.R.layout.simple_spinner_dropdown_item,
				MainActivity.allPositions));
		submitButton = (Button) findViewById(R.id.button1);

		createPositionButton = (Button) findViewById(R.id.button2);
		createPositionButton.setText("Create a new Position");
		createPositionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getBaseContext(),
						CreatePositionActivity.class));
			}

		});
	}

	public void setUiDefaults() {
		setStandardForm();
		setActionBarTitle();
		setFormArr();
		createSubmitButton();
	}

	public void createSubmitButton() {
		submitButton.setText("Submit Canidate");
		submitButton.setOnClickListener(this);
	}

	public void setStandardForm() {
		FormView.setStandardFormOptions(mFormArr);
	}

	public void setActionBarTitle() {
		getSupportActionBar().setTitle(getString(R.string.createInter));

	}

	public void setFormArr() {
		mFormArr[0].getTextView().setText(getString(R.string.fName));
		mFormArr[1].getTextView().setText(getString(R.string.mName));
		mFormArr[2].getTextView().setText(getString(R.string.lName));
	}

	public boolean isNull(String[] s) {
		for (String in : s) {
			if (in == null || in.equals("")) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void onClick(View clickedView) {
		Position p = (Position) mSpinner.getSelectedItem();

		mSpinner.setAdapter(new ArrayAdapter(getBaseContext(),
				android.R.layout.simple_spinner_dropdown_item,
				MainActivity.allPositions));
		String firstName = mFormArr[0].getEditText().getText().toString();
		String middleName = mFormArr[1].getEditText().getText().toString();
		String lastName = mFormArr[2].getEditText().getText().toString();

		Position selectedPos = (p != null) ? p : (Position) mSpinner
				.getSelectedItem();

		Map<String, String> attrs = new HashMap<String, String>();
		attrs.put("name", firstName + " " + middleName + " " + lastName);
		attrs.put("school", "");
		attrs.put("graddate", "");
		attrs.put("photoLocation", "");

		Candidate c = new Candidate(attrs, selectedPos);

		MainActivity.allCandidates.add(c);

		startActivity(new Intent(getBaseContext(), MainActivity.class));
		finish();
	}
}
