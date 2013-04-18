package com.espinosoft.softour;


public interface Callbacks {
	/**
	 * Callback for when an item has been selected.
	 */
	public void onItemSelected(String id);
	public void onItemSelected(Object object);
}