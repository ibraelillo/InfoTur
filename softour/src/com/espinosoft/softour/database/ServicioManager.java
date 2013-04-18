package com.espinosoft.softour.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

public class ServicioManager{
	private Context context;
	private DaoOpenHelper helper;
	private Dao<Servicio, Integer> dao;
	
	public ServicioManager(Context context){
		this.context = context;
		helper = OpenHelperManager.getHelper(context, DaoOpenHelper.class);		
				
		try {
			dao = helper.getDao(Servicio.class);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public Servicio find(int id) throws Exception{
		return dao.queryForId(id);
	}
	
	
	public List<Servicio> save(List<Servicio> servicios){
		
		try{
			boolean conflict = false;
		
			for(Servicio n: servicios){
				conflict &= conflict & save(n);
				
				if(conflict)
					Log.i("ServicioManager", "No se ha podido guardar el Servicio " + n.getNombre());
			}		
			return dao.queryForAll();
			
		}catch(SQLException e){
			return new ArrayList<Servicio>();
		}
	}
	
	
	public boolean save(Servicio n)
	{
		boolean conflict = false;
		
		try {
			if(dao == null)				
				dao = helper.getDao(Servicio.class);
			
			dao.createOrUpdate(n);
			
			
		} catch (SQLException e1) {
			Log.e("Error", e1.getMessage());			
			e1.printStackTrace();
		}
		catch(Exception e){
			//Toast.makeText(context, e.getMessage() != null ? e.getMessage() : "Ha ocurrido un error: "+ e.getCause().getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		return conflict;
	}
	
	/**
	 * Devuelve todas las Servicios
	 * @return
	 */
	public List<Servicio> getServicios(){
		List<Servicio> servicios;
		try {
			servicios = dao.queryForAll();
			return servicios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void destroy(){
		helper.close();
	}
	
	public void loadData(LatLng position) throws SQLException {
		
		
		Cliente c = new Cliente();
		c.setDao(helper.getDao(Cliente.class));
		c.setTelefono("06 51 99 55 78");
		c.setDireccion("Residence parc de Beauville Apt 340, 80000 Amiens");
		c.setOwner(true);
		c.create();
		double x = position.latitude;
		double y = position.longitude;
		
		for (int i = 0; i < 3; i++) {
			x += (i % 2 ==0 ?  0.001 : -0.002);
			y += (i % 2 ==0 ?  0.002 : -0.001);
			Servicio s = new Servicio();
			s.setDao(dao);
			Random r = new Random();

			
			s.setDescripcion("prueba de servicio " + i)
					.setNombre("Servicio " + i)
					.setLink_reserva(
							"http://www.iamatestservice.com/just/test/me")
					.setUbicacion_latitud(x)
					.setUbicacion_longitud(y)
					.setReserva_en_linea(i % 2 == 0)
					.setCliente(c)
					.setDescripcion("Servicio #" + i +" descripcion")
					.create();
			
			Log.i("Saved service", s.getNombre());
		}
	}
}
