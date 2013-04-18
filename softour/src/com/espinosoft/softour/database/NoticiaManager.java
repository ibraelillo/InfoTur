package com.espinosoft.softour.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.espinosoft.softour.news.Noticia;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class NoticiaManager{

	public static String TABLE_NAME = "noticia";
	public static String[] COLUMN_NAMES = {"id", "titulo", "texto", "fecha", "link", "foto", "guid"};
	private SQLiteDatabase db;
	private Context context;
	private DaoOpenHelper helper;
	private DaoManager manager;
	private Dao<Noticia, Integer> dao;
	private ConnectionSource source;
	
	
	public NoticiaManager(Context context){
		this.context = context;
		helper = OpenHelperManager.getHelper(context, DaoOpenHelper.class);		
				
		try {
			dao = helper.getDao(Noticia.class);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public Noticia find(int id) throws Exception{
		return dao.queryForId(id);
	}
	
	
	public List<Noticia> save(List<Noticia> noticias){
		
		try{
			boolean conflict = false;
		
			for(Noticia n: noticias){
				conflict &= conflict & save(n);
				
				if(conflict)
					Log.i("NoticiaManager", "No se ha podido guardar la noticia " + n.getTitulo());
			}		
			return dao.queryForAll();
			
		}catch(SQLException e){
			return new ArrayList<Noticia>();
		}
	}
	
	
	public boolean save(Noticia n)
	{
		boolean conflict = false;
		
		try {
			if(dao == null)				
				dao = helper.getDao(Noticia.class);
			
			dao.createOrUpdate(n);
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		catch(Exception e){
			//Toast.makeText(context, e.getMessage() != null ? e.getMessage() : "Ha ocurrido un error: "+ e.getCause().getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		return conflict;
	}
	
	/**
	 * Devuelve todas las noticias
	 * @return
	 */
	public List<Noticia> getNoticias(){
		List<Noticia> noticias;
		try {
			noticias = dao.queryForAll();
			return noticias;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void destroy(){
		helper.close();
	}
}
