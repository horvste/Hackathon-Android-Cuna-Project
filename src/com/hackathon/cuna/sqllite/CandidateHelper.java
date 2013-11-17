package com.hackathon.cuna.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CandidateHelper extends SQLiteOpenHelper{

  public static final String TABLE_CANDIDATES = "candidates";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_ATTRIBUTES = "attributes";
  public static final String COLUMN_APPLIEDPOSITION = "appliedposition";

  private static final String DATABASE_NAME = "candidates.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_CANDIDATES + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_ATTRIBUTES
      + " text not null, " + COLUMN_APPLIEDPOSITION + " text not null);";

  public CandidateHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(PositionsHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANDIDATES);
    onCreate(db);
  }
  
  public Context getContext(){
	  return this.getContext();
  }

} 