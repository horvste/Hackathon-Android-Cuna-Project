package com.hackathon.cuna.sqllite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.hackathon.cuna.position.Candidate;
import com.hackathon.cuna.position.Position;

public class CandidateDAO {

	// Database fields
	private SQLiteDatabase database;
	private CandidateHelper dbHelper;
	private String[] allColumns = { CandidateHelper.COLUMN_ID,
			CandidateHelper.COLUMN_ATTRIBUTES,
			CandidateHelper.COLUMN_APPLIEDPOSITION };

	public CandidateDAO(Context context) {
		dbHelper = new CandidateHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Saves the specified Candidate to the database
	 * 
	 * @param pos
	 * @return
	 */
	public void saveCandidate(Candidate can) {
		ContentValues values = new ContentValues();
		values.put(CandidateHelper.COLUMN_ATTRIBUTES, candidateMapString(can));
		values.put(CandidateHelper.COLUMN_APPLIEDPOSITION, can
				.getAppliedPosition().getName());
		database.insert(PositionsHelper.TABLE_POSITIONS, null, values);
	}

	/**
	 * Deletes the specified position
	 * 
	 * @param pos
	 */
	public void deleteCandidate(Candidate can) {
		String attrs = candidateMapString(can);
		System.out.println("Comment deleted with name: " + attrs);
		database.delete(CandidateHelper.TABLE_CANDIDATES,
				CandidateHelper.COLUMN_ATTRIBUTES + " = " + attrs, null);
	}

	/**
	 * Returns all candidates stored in database
	 * 
	 * @return
	 */
	public List<Candidate> getAllCandidates() {
		List<Candidate> cans = new ArrayList<Candidate>();

		Cursor cursor = database.query(CandidateHelper.TABLE_CANDIDATES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Candidate can = cursorToCandidate(cursor);
			cans.add(can);
			cursor.moveToNext();
		}
		cursor.close();
		return cans;
	}

	/**
	 * Creates the Candidate held at a cursor
	 * 
	 * @param cursor
	 * @return
	 */
	private Candidate cursorToCandidate(Cursor cursor) {
		Map<String, String> attrs = decodeMapString(cursor.getString(1));
		String posName = cursor.getString(2);
		Position p = null; // Otherwise error.. Poor coding.

		PositionDAO posDAO = new PositionDAO(dbHelper.getContext());
		posDAO.open();

		for (Position pos : posDAO.getAllPositions()) {
			if (pos.getName().equals(posName)) {
				p = pos;
				break;
			}
		}
		posDAO.close();

		Candidate can = new Candidate(attrs, p);
		return can;
	}

	private Map<String, String> decodeMapString(String mapStr) {
		Map<String, String> attrs = new HashMap<String, String>();

		String[] pairs = mapStr.split("$$$$");

		for (String keyValue : pairs) {
			String[] keyAndValue = keyValue.split(";;");
			attrs.put(keyAndValue[0], keyAndValue[1]);
		}

		return attrs;
	}

	private String candidateMapString(Candidate can) {
		StringBuffer buff = new StringBuffer();

		for (Map.Entry<String, String> pair : can.getAttributes().entrySet()) {
			buff.append(pair.getKey() + ";;" + pair.getValue() + "$$$$");
		}

		buff.delete(buff.length() - 4, buff.length());
		return buff.toString();
	}
}