package com.espinosoft.softour.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.espinosoft.softour.R;

public class LocationSqliteOpenHelper extends SQLiteOpenHelper {

	public LocationSqliteOpenHelper(Context context, CursorFactory factory, int version) {
		super(context, context.getString(R.string.database_name), factory, version <= 0 ? 1 : version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table noticia" +
				"(id integer primary key, titulo varchar(255), texto text, link text, fecha varchar(50), foto text, guid text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
		
		db.execSQL("drop table if exists noticia");
		onCreate(db);		
	}

}
