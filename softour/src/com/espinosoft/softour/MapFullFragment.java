package com.espinosoft.softour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.espinosoft.softour.database.Servicio;
import com.espinosoft.softour.database.ServicioManager;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MapFullFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link MapFullFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class MapFullFragment extends FragmentWithMap {

	// private OnFragmentInteractionListener mListener;

	SupportMapFragment fragment;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment MapFullFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MapFullFragment newInstance() {
		MapFullFragment fragment = new MapFullFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public MapFullFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			// mParam1 = getArguments().getString(ARG_PARAM1);
			// mParam2 = getArguments().getString(ARG_PARAM2);
		}
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
		return inflater.inflate(R.layout.fragment_map_full, null, false);

	}

	@Override
	public void onViewCreated(View view, Bundle bundle) {

		super.onViewCreated(view, bundle);
		FragmentManager fm = getChildFragmentManager();
		fragment = (SupportMapFragment) getChildFragmentManager()
				.findFragmentById(R.id.map_fragment);

		if (fragment == null) {
			fragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map_full_container, fragment)
					.commit();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (map == null) {
			map = fragment.getMap();
			setupMap();
			
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			// mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// mListener = null;
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

	public void addLocations(List<Locatable> locations) {

		for (Locatable c : locations) {

			Log.i("Location", c.getPosition().toString());

			MarkerOptions marker = new MarkerOptions();
			marker
				.position(c.getPosition())
				.title(c.getNombre())
				.icon(BitmapDescriptorFactory.fromResource(c.getIcon()))
				.snippet("");
			
			
			map.addMarker(marker);
		}

	}
	
	@Override
	public void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		addLocations(getLocations());
	}

	/**
	 * Devuelve una lista de locationes de prueba
	 * 
	 * @return
	 */
	public List<Locatable> getLocations() {
		List<Locatable> locations = new ArrayList<Locatable>();
		ServicioManager manager = new ServicioManager(getActivity());
		try {
			manager.loadData(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()));

			List<Servicio> servicios = manager.getServicios();

			for (Servicio service : servicios) {
				locations.add((Locatable)service);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return locations;
	}
}
