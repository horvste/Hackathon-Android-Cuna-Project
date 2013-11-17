package com.hackathon.cuna.customviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackathon.kuna.R;

/**
 * 
 * @author Steven Horvatin
 * 
 */
public class FormView extends LinearLayout {
	/**
	 * Views the text that you set with setFormText(String s);
	 */
	private TextView mFormTextView;
	/**
	 * User input from this editText
	 */
	private EditText mFormEditText;

	/**
	 * Takes in context parameter and attribute set of the view
	 * 
	 * @param context
	 * @param attrs
	 */
	public FormView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.form_view, this);
		findViewsById();
	}

	/**
	 * Finds the views by id of the instance variables
	 * {@link FormView.mFormTextView}
	 */
	public void findViewsById() {
		mFormEditText = (EditText) findViewById(R.id.editText1);
		mFormTextView = (TextView) findViewById(R.id.textView1);
	}

	public void setFormText(String s) {
		mFormTextView.setText(s);
	}

	public String getUserInputText() {
		return mFormEditText.getText().toString();
	}

	/**
	 * Changes the ime options for the EditText to 'Next'
	 */
	public void setImeToNext() {
		mFormEditText.setInputType(InputType.TYPE_CLASS_TEXT);
		mFormEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
	}

	/**
	 * Changes the ime options for the EditText to 'Done'
	 */
	public void setImeToDone() {
		mFormEditText.setInputType(InputType.TYPE_CLASS_TEXT);
		mFormEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
	}

	public static void setStandardFormOptions(List<FormView> formViews) {
		int size = formViews.size() - 1;
		for (int i = 0; i < formViews.size() - 1; i++) {
			formViews.get(i).setImeToNext();
		}
		formViews.get(formViews.size() - 1).setImeToDone();
	}

	public static void setStandardFormOptions(FormView[] arr) {
//		List<FormView> formViews = new ArrayList<FormView>();
//		for (FormView formView : arr) {
//			formViews.add(formView);
//		}
		setStandardFormOptions(Arrays.asList(arr));
		
	}

	/**
	 * get mFormEditText instance variable.
	 * 
	 * @return
	 */

	public TextView getTextView() {
		return mFormTextView;
	}

	/**
	 * Set the mFormTextView to the specified parameter
	 * 
	 * @param mFormTextView
	 */
	public void setTextView(TextView mFormTextView) {
		this.mFormTextView = mFormTextView;
	}

	/**
	 * get the EditText mFormEditText instance variable
	 */
	public EditText getEditText() {
		return mFormEditText;
	}

	/**
	 * set the mFormEditText to the specified parameter
	 * 
	 * @param mFormEditText
	 */
	public void setEditText(EditText mFormEditText) {
		this.mFormEditText = mFormEditText;
	}

}
