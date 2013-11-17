package com.hackathon.cuna.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathon.cuna.CreateInterviewerActivity;
import com.hackathon.cuna.MainActivity;
import com.hackathon.cuna.position.Candidate;
import com.hackathon.kuna.CanidateDisplay;
import com.hackathon.kuna.R;

@SuppressLint("ValidFragment")
public class InterviewFragment extends BaseFragment implements OnClickListener,
		OnItemClickListener {
	/**
	 * arr contains a button array with [0] being "Create New Interviewer" and
	 * [1] being "Load Interviewers"
	 */
	private Button[] buttonArr;
	/**
	 * interview list view contains the current interviews created
	 */
	private ListView interListView;
	private ArrayAdapter interAdapter;

	public InterviewFragment() {
		super();
	}

	public InterviewFragment(int resource) {
		super(resource);
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void initComp() {
		buttonArr = new Button[1];
	}

	@Override
	public void findViewsById() {
		buttonArr[0] = (Button) super.viewInflated.findViewById(R.id.button1);
		interListView = (ListView) super.viewInflated
				.findViewById(R.id.listView1);
		interListView.setAdapter(new ArrayAdapter(getActivity()
				.getBaseContext(), android.R.layout.simple_list_item_1,
				MainActivity.allCandidates));
	}

	@Override
	public void setupUi() {
		interListView.setOnItemClickListener(this);
		for (Button in : buttonArr) {
			in.setOnClickListener(this);
		}
		buttonArr[0].setText("Create New Candidate");
	}

	@Override
	public void onClick(View clickedView) {
		if (clickedView.getId() == R.id.button1) {
			startActivity(new Intent(getActivity().getBaseContext(),
					CreateInterviewerActivity.class));
			getActivity().finish();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> adapView, View v, int pos, long arg3) {
		Candidate c = (Candidate) this.interListView.getItemAtPosition(pos);

		String push = c.getName()  + "\n\t\t\t\t" + c.getAppliedPosition().getName();
		startActivity(new Intent(getActivity().getBaseContext(),
				CanidateDisplay.class).putExtra("push", push));

	}

}
