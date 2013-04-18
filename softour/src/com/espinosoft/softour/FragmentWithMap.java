package com.espinosoft.softour;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public abstract class FragmentWithMap extends Fragment implements
		LocationListener, OnMarkerClickListener, OnInfoWindowClickListener,
		OnItemClickListener {

	protected GoogleMap map;

	protected Location lastLocation;

	LocationManager locManager;

	private com.espinosoft.softour.Callbacks mCallbacks;

	
	/**
	 * Este metodo debe ser programado preferentemente dentro del metodo onViewCreated
	 * Debe devolver el mapa asociado a una vista o un fragmento de google
	 * @return
	 */
	protected GoogleMap getMap(){
		return map;
	}
	
	/**
	 * @return the lastLocation
	 */
	public Location getLastLocation() {
		return lastLocation;
	}

	/**
	 * @param lastLocation
	 *            the lastLocation to set
	 */
	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	/**
	 * @return the locManager
	 */
	public LocationManager getLocManager() {
		return locManager;
	}

	public void StartListening() {

		// checkGooglePlayServicesAvailability();
		locManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		Criteria req = new Criteria();
		req.setAccuracy(Criteria.ACCURACY_FINE);

		// Mejor proveedor por criterio
		// String mejorProviderCrit = locManager.getBestProvider(req, false);
		// Lista de proveedores por criterio
		// List<String> listaProvidersCrit = locManager.getProviders(req,
		// false);

		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
				|| !locManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			// mostrarAvisoGpsDeshabilitado();
		}

		lastLocation = locManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				1000, 1, this);
	}

	public void setupMap() throws NullPointerException {

		if(getMap() == null)
			throw new NullPointerException("El mapa no se ha cargado o no se puede cargar");
		try {
			MapsInitializer.initialize(getActivity());
			// Hide the zoom controls as the button panel will cover it.
			getMap().getUiSettings().setZoomControlsEnabled(false);

			
			// map.se

			// Setting an info window adapter allows us to change the both the
			// contents and look of the
			// info window.
			// map.setInfoWindowAdapter(new InfoWindowAdapter());

			// Set listeners for marker events. See the bottom of this class for
			// their behavior.
			getMap().setOnMarkerClickListener(this);
			getMap().setOnInfoWindowClickListener(this);

			// Add lots of markers to the map.
			addMyLocationToMap();

			
			// Pan to see all markers in view.
			// Cannot zoom to bounds until the map has a size.
			/*
			 * final View mapView = getFragmentManager().findFragmentById(
			 * R.id.map_fragment).getView(); if
			 * (mapView.getViewTreeObserver().isAlive()) {
			 * mapView.getViewTreeObserver().addOnGlobalLayoutListener( new
			 * OnGlobalLayoutListener() {
			 * 
			 * @SuppressLint("NewApi") // We check which build version we are using.
			 * 
			 * @Override public void onGlobalLayout() { LatLngBounds.Builder bld =
			 * new LatLngBounds.Builder();
			 * 
			 * for (int i = 0; i < mAvailableCars.size(); i++) {
			 * 
			 * LatLng ll = new LatLng(Cars.get(i).getPos() .getLat(),
			 * Cars.get(i).getPos() .getLon()); bld.include(ll);
			 * 
			 * } LatLngBounds bounds = bld.build();
			 * map.moveCamera(CameraUpdateFactory .newLatLngBounds(bounds, 70));
			 * mapView.getViewTreeObserver() .removeGlobalOnLayoutListener(this);
			 * 
			 * } }); }
			 */
		} catch (GooglePlayServicesNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onDestroyView() {
		locManager.removeUpdates(this);
		
		super.onDestroyView();
	}

	/**
	 * 
	 */
	public void addMyLocationToMap() {
		if (map != null && lastLocation != null) {
			LatLng yo = new LatLng(lastLocation.getLatitude(),
					lastLocation.getLongitude());
			map.addMarker(new MarkerOptions().position(
					new LatLng(lastLocation.getLatitude(), lastLocation
							.getLongitude()))
							.title(getString(R.string.app_name))
							.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_myposition))						
					);

			
			
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(yo, 15));
			map.setMyLocationEnabled(true);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.i("Cambio de position",
				"Long: " + "Long: " + location.getLongitude() + "\nLat: "
						+ location.getLatitude());
		if (map != null) {
			map.clear();
			this.lastLocation = location;

			addMyLocationToMap();
			Log.i("Cambio de position",
					"Long: " + "Long: " + location.getLongitude() + "\nLat: "
							+ location.getLatitude());
			Toast.makeText(
				getActivity(),
				"Long: " + location.getLongitude() + "\nLat: "
						+ location.getLatitude(), Toast.LENGTH_SHORT)
				.show();
		}
		/*
		 * map.addMarker( new MarkerOptions() .position(new
		 * LatLng(location.getLatitude(), location.getLongitude()))
		 * .title(getString(R.string.app_name)) .snippet("Long: " +
		 * location.getLongitude() + "\nLat: " + location.getLatitude()) );
		 */

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			if (!(activity instanceof Callbacks)) {
				throw new IllegalStateException(
						"Activity must implement fragment's callbacks.");
			}

			mCallbacks = (Callbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}
