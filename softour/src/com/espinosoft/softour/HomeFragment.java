package com.espinosoft.softour;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link HomeFragment#newInstance} factory method to create an instance of this
 * fragment.
 * 
 */
public class HomeFragment extends FragmentWithMap {

	//GoogleMap map;

	//Location location = null;

	//LocationManager locManager;

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	public HomeFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		try {
			MapsInitializer.initialize(getActivity());
		} catch (GooglePlayServicesNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StartListening();	
	}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View v = inflater.inflate(R.layout.fragment_home, container, false);
		this.map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
		
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle saveInstanceState){
		super.onViewCreated(view, saveInstanceState);
		if( map!=null )
			setupMap();
	}
	
	@Override
	public void onDestroyView(){
		super.onDestroyView();
	}

	

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	

}
