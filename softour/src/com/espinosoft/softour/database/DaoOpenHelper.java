package com.espinosoft.softour.database;

import java.sql.SQLException;
import java.util.Random;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.espinosoft.softour.R;
import com.espinosoft.softour.news.Noticia;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DaoOpenHelper extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "location.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the SimpleData table

	public DaoOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource source) {
		// TODO Auto-generated method stub

		try {
			Log.i(DaoOpenHelper.class.getName(), "onCreate");
			TableUtils.createTable(source, Noticia.class);
			TableUtils.createTable(source, Cliente.class);
			TableUtils.createTable(source, Actividad.class);
			TableUtils.createTable(source, Servicio.class);

			//loadData();
			//* code change

		} catch (SQLException e) {
			Log.e(DaoOpenHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DaoOpenHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Noticia.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DaoOpenHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
	}

	

}
