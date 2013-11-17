package com.hackathon.cuna.sqllite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.hackathon.cuna.position.Position;
import com.hackathon.cuna.position.Requirement;

public class PositionDAO {

	// Database fields
	private SQLiteDatabase database;
	private PositionsHelper dbHelper;
	private String[] allColumns = { PositionsHelper.COLUMN_ID,
			PositionsHelper.COLUMN_NAME, PositionsHelper.COLUMN_DESCRIPTION,
			PositionsHelper.COLUMN_REQUIREMENTS };

	public PositionDAO(Context context) {
		dbHelper = new PositionsHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Saves the specified Position to the database
	 * 
	 * @param pos
	 * @return
	 */
	public void savePosition(Position pos) {
		ContentValues values = new ContentValues();
		values.put(PositionsHelper.COLUMN_NAME, pos.getName());
		values.put(PositionsHelper.COLUMN_DESCRIPTION, pos.getDescription());
		values.put(PositionsHelper.COLUMN_REQUIREMENTS,
				requirementStore(pos.getRequirements()));
		database.insert(PositionsHelper.TABLE_POSITIONS, null, values);
	}

	private String requirementStore(List<Requirement> requirements) {
		System.out.println("RequirementsList" + requirements);
		StringBuffer buff = new StringBuffer();

		for (Requirement req : requirements) {
			buff.append(req.description + ";;" + req.weight + "$$$$");
		}

		buff.delete(buff.length() - 4, buff.length());
		System.out.println("requirementstore" + buff.toString());
		return buff.toString();
	}

	/**
	 * Deletes the specified position
	 * 
	 * @param pos
	 */
	public void deletePosition(Position pos) {
		String name = pos.getName();
		System.out.println("Comment deleted with name: " + name);
		database.delete(PositionsHelper.TABLE_POSITIONS,
				PositionsHelper.COLUMN_NAME + " = " + name, null);
	}

	public List<Position> getAllPositions() {
		List<Position> positions = new ArrayList<Position>();

		Cursor cursor = database.query(PositionsHelper.TABLE_POSITIONS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Position pos = cursorToPos(cursor);
			positions.add(pos);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return positions;
	}

	private Position cursorToPos(Cursor cursor) {
		System.out.println("CURSOR" + cursor.toString());

		String name = cursor.getString(1);
		String desc = cursor.getString(2);
		List<Requirement> reqs = new ArrayList<Requirement>();

		String encodedReqs = cursor.getString(3);
		System.out.println("encodedReqs = " + encodedReqs.toString());
		String[] individualReqs = encodedReqs.split("$$$$");
		for (String s : individualReqs) {
			String[] params = s.split(";;");
			/*
			 * Length is 1 but you are calling it at [1]. meaning the array only
			 * has 1 index [0]
			 */
			reqs.add(new Requirement(params[0], Double.valueOf(params[1])));
		}

		Position pos = new Position(name, desc, reqs);
		return pos;
	}
}