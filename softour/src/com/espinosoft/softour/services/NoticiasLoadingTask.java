package com.espinosoft.softour.services;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.AbsListView;

import com.espinosoft.softour.R;
import com.espinosoft.softour.database.NoticiaManager;
import com.espinosoft.softour.news.Noticia;
import com.espinosoft.softour.news.Parser;

public class NoticiasLoadingTask extends AsyncTask<Void, Void, Void> {
	Context context;
    ProgressDialog pDialog;
    AbsListView list;
    Parser parser;
    NoticiaManager manager;
    
 
    public NoticiasLoadingTask(Context context, AbsListView list){
        this.context = context;
        this.list = list;        
    }
 
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
         
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getString(R.string.retrieve_data_title));
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
        manager = new NoticiaManager(context);
        
        try {
			parser = new Parser("http://elpais.com/tag/rss/ocio/a/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
    }
 
    @Override
    protected Void doInBackground(Void... arg0) {
        // TODO Auto-generated method stub
    	List<Noticia> noticias = new ArrayList<Noticia>();
    	
        try{

            pDialog.setMessage(context.getString(R.string.loading_data));
            noticias = parser.Parse();
            
            noticias  = manager.save(noticias);
            Thread.sleep(2000);            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
 
    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
    	pDialog.setIcon(R.drawable.ok);
        super.onPostExecute(result);
        pDialog.dismiss();       
        
    }
}
