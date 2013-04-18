package com.espinosoft.softour.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.espinosoft.softour.R;

public class DashboardAdapter implements ListAdapter{

	List<Item> items;
	Context context;
	
	public DashboardAdapter(Context context, List<Item> items) {
		//super(context, textViewResourceId);
		this.context = context;
		this.items = items != null ? items : new ArrayList<DashboardAdapter.Item>();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = items.get(position);
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(this.context);
			
			View v = inflater.inflate(R.layout.dashboard_item, parent);
			ViewHolder.title = (TextView)v.findViewById(R.id.textView1);
			ViewHolder.icon = (ImageView)v.findViewById(R.id.list_item_goto);
		}
		
		ViewHolder.title.setText(item.getTitle());		
		ViewHolder.icon.setImageResource(item.getIcon());
		
		return convertView;
	}
	
	
	public List<Item> getItems(){
		
		items.add(new Item(R.drawable.ic_list_star, "Services"));
		items.add(new Item(R.drawable.ic_list_star, "Services"));
		items.add(new Item(R.drawable.ic_list_star, "Services"));
		items.add(new Item(R.drawable.ic_list_star, "Services"));
		
		return items;		
	}
	
	
	private class Item{
		private int resource_icon;
		private int resource_text;
		private String text;
		
		/**
		 * @return the resource_text
		 */
		public int getTitleResource() {
			return resource_text;
		}

		/**
		 * @param resource_text the resource_text to set
		 */
		public void setTitle(int resource_text) {
			this.resource_text = resource_text;
		}

		/**
		 * @return the text
		 */
		public String getTitle() {
			return text;
		}

		/**
		 * @param text the text to set
		 */
		public void setTitle(String text) {
			this.text = text;
		}

		public Item(int resource_icon, int resource_text, String text) {
			
			this.setIcon(resource_icon);
			this.resource_text = resource_text;
			this.text = text;
		}
		
		public Item(int resource_icon, String text) {
			
			this.setIcon(resource_icon);
			this.text = text;
		}
		
		

		/**
		 * @return the resource_icon
		 */
		private int getIcon() {
			return resource_icon;
		}

		/**
		 * @param resource_icon the resource_icon to set
		 */
		private void setIcon(int resource_icon) {
			this.resource_icon = resource_icon;
		}		
	}
	
	static class ViewHolder{
		static TextView title;
		static ImageView icon;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}
}
