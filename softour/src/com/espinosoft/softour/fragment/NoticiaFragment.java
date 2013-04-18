package com.espinosoft.softour.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.espinosoft.softour.Callbacks;
import com.espinosoft.softour.R;
import com.espinosoft.softour.news.NoticiasAdapter;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the
 * ListView with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class NoticiaFragment extends Fragment implements
		OnItemClickListener {

	

	private com.espinosoft.softour.Callbacks mCallbacks;
	
	//private OnFragmentInteractionListener mListener;

	/**
	 * The fragment's ListView/GridView.
	 */
	private AbsListView mListView;

	
	
	/**
	 * The Adapter which will be used to populate the ListView/GridView with
	 * Views.
	 */
	private ListAdapter mAdapter;

	// TODO: Rename and change types of parameters
	public static NoticiaFragment newInstance() {
		NoticiaFragment fragment = new NoticiaFragment();
		return fragment;
	}

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public NoticiaFragment() {
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		
		super.onActivityCreated(savedInstanceState);
		
		getActivity().getActionBar().setTitle(getString(R.string.app_name)+ " - " + getString(R.string.title_seccion_noticias));
		
		// Set the adapter
		mListView = (ListView)this.getView().findViewById(android.R.id.list);
		
		// Set OnItemClickListener so we can be notified on item clicks
		mListView.setOnItemClickListener(this);
		mAdapter = new NoticiasAdapter(getActivity());
		mListView.setAdapter(mAdapter);
		
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View v = inflater.inflate(R.layout.fragment_noticia, container,	false);
		return v;
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
	public void onDetach() {
		super.onDetach();
		//mListener = null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Object o = parent.getAdapter().getItem(position);
		
		
		
		mCallbacks.onItemSelected(o);		
	}


}
