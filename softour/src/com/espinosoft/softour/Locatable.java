package com.espinosoft.softour;

import com.google.android.gms.maps.model.LatLng;

public interface Locatable {
	
	public LatLng getPosition();
	public String getNombre();
	public int getIcon(); 
	public Object getObjectSource();
}
