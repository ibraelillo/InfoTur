/**
 * 
 */
package com.espinosoft.softour.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.espinosoft.softour.R;
import com.espinosoft.softour.database.NoticiaManager;
import com.espinosoft.softour.util.Util;

/**
 * @author Ibrael
 *
 */
public class NoticiasAdapter extends ArrayAdapter<Noticia> {

	List<Noticia> noticias = new ArrayList<Noticia>();
	NoticiaManager manager;
	
	/**
	 * @return the noticias
	 */
	public List<Noticia> getNoticias() {
		return manager.getNoticias();
	}


	/**
	 * @param noticias the noticias to set
	 */
	public void setNoticias(List<Noticia> noticias) {
		if(noticias != null)
			this.noticias = noticias;
	}


	LayoutInflater inflater;
	
	public NoticiasAdapter(Context context) {
		super(context, R.layout.fragment_noticia);	
		
		manager = new NoticiaManager(context);
		
		inflater = LayoutInflater.from(context);
		
		update();
	}
	
	public void update(){
		noticias = manager.getNoticias();
		notifyDataSetChanged();
	}
	
	
	@Override
	public int getCount(){
		return this.noticias.size();
	}
	
	@Override
	public View getView(int position, View convertview, ViewGroup parent){
				
		View rowview = convertview;
		Noticia n = noticias.get(position);
		if(rowview == null){
			rowview = inflater.inflate(R.layout.simple_list_icon_star, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.date = (TextView)rowview.findViewById(R.id.list_item_date);
			viewHolder.text = (TextView)rowview.findViewById(R.id.list_item_title);
			viewHolder.icon = (ImageView)rowview.findViewById(R.id.list_item_icon);
			
			rowview.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowview.getTag();
		holder.text.setText(n.getTitulo());
		holder.date.setText(Util.getFriendlyTime(new Date(n.getFecha())));
		
		
		return rowview;
	}
	
	@Override
	public Noticia getItem(int position){
		return this.noticias.get(position);
	}
	
	
	static class ViewHolder{
		public TextView text;
		public TextView date;
		public ImageView icon;
	}

}
