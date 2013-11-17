package com.hackathon.kuna;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.hackathon.cuna.MainActivity;
import com.hackathon.cuna.customviews.FormView;
import com.hackathon.cuna.position.Position;
import com.hackathon.cuna.position.Requirement;
import com.hackathon.cuna.sqllite.PositionDAO;

public class CreatePositionActivity extends Activity implements OnClickListener {
	protected FormView nameFormView;
	protected FormView descFormView;
	protected FormView ReqFormView;
	protected Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_position);
		findViewsById();
		setupUi();
	}

	public void findViewsById() {
		nameFormView = (FormView) findViewById(R.id.formView1);
		descFormView = (FormView) findViewById(R.id.formView2);
		ReqFormView = (FormView) findViewById(R.id.formView3);
		submitButton = (Button) findViewById(R.id.button1);
	}

	public void setupUi() {
		submitButton.setText("Create Position");
		submitButton.setOnClickListener(this);

		nameFormView.getTextView().setText("Name: ");
		descFormView.getTextView().setText("Description: ");
		ReqFormView.getTextView().setText("Requirements: ");

	}

	@Override
	public void onClick(View arg0) {
		String name = nameFormView.getEditText().getText().toString();
		String desc = descFormView.getEditText().getText().toString();
		
		List<Requirement> reqList = new ArrayList<Requirement>();
		for (int i = 0; i < 3; i++){
			Requirement r = new Requirement("Test Requirement " + i, 1);
			reqList.add(r);
		}
		
		MainActivity.allPositions.add(new Position(name, desc, reqList));
		Toast.makeText(this.getBaseContext(), "Saved new position " + name, Toast.LENGTH_SHORT).show();
		finish();
	}

}
