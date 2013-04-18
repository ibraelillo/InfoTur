package com.espinosoft.softour;

import com.espinosoft.softour.database.NoticiaManager;
import com.espinosoft.softour.news.Noticia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link NoticiaDetailFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class NoticiaDetailFragment extends Fragment {
	
	Noticia noticia;
	
	public static final String  NOTICIA_ID = "noticia_id";

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment NoticiaDetailFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static NoticiaDetailFragment newInstance(int id) {
		NoticiaDetailFragment fragment = new NoticiaDetailFragment();
		Bundle args = new Bundle();
		args.putInt(NOTICIA_ID, id);
		fragment.setArguments(args);
		return fragment;
	}

	public NoticiaDetailFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			int id = getArguments().getInt(NOTICIA_ID);
			NoticiaManager manager = new NoticiaManager(getActivity());
			
			try{
				noticia = manager.find(id);
			}catch(Exception e){
				Toast.makeText(getActivity(), e.getMessage()!= null ? e.getMessage() : e.getCause().getMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_noticia_detail, container, false);
		
		TextView titulo = (TextView)v.findViewById(R.id.title_noticia);
		titulo.setText(noticia.getTitulo());
		
		TextView fecha = (TextView)v.findViewById(R.id.fecha_noticia);
		fecha.setText(noticia.getFecha());
		
		TextView texto = (TextView)v.findViewById(R.id.texto_noticia);
		texto.setText(noticia.getDescripcion());
		
		TextView link = (TextView)v.findViewById(R.id.link_noticia);
		link.setText(noticia.getLink());
		
		
		return v;
	}

}
