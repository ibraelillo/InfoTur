package com.espinosoft.softour.database;

import com.espinosoft.softour.Locatable;
import com.espinosoft.softour.R;
import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("rawtypes")
@DatabaseTable(tableName = "servicio")
public class Servicio extends BaseDaoEnabled implements Locatable{
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull = false)
	private String nombre;
	
	@DatabaseField(dataType = DataType.LONG_STRING)
	private String descripcion;
	
	@DatabaseField
	private boolean reserva_en_linea;
	
	@DatabaseField
	private String link_reserva;
	
	@DatabaseField(canBeNull = true, foreign = true)
	private Cliente cliente;

	@DatabaseField
	private double ubicacion_latitud;
	
	@DatabaseField
	private double ubicacion_longitud;
	
	
	
	/**
	 * interfaz locatable
	 */
	@Override
	public LatLng getPosition() {
		LatLng pos = new LatLng(getUbicacion_latitud(),getUbicacion_longitud());
		return pos;
	}

	

	@Override
	public int getIcon() {
		// TODO Auto-generated method stub
		return R.drawable.ic_location_services;
	}

	@Override
	public Object getObjectSource() {
		return this;
	}
	
	
	
	
	/**
	 * @return the ubicacion_latitud
	 */
	public double getUbicacion_latitud() {
		return ubicacion_latitud;
	}

	/**
	 * @param ubicacion_latitud the ubicacion_latitud to set
	 */
	public Servicio setUbicacion_latitud(double ubicacion_latitud) {
		this.ubicacion_latitud = ubicacion_latitud;
		return this;
	}

	/**
	 * @return the ubicacion_longitud
	 */
	public double getUbicacion_longitud() {
		return ubicacion_longitud;
	}

	/**
	 * @param ubicacion_longitud the ubicacion_longitud to set
	 */
	public Servicio setUbicacion_longitud(double ubicacion_longitud) {
		this.ubicacion_longitud = ubicacion_longitud;
		return this;
	}
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 * @return 
	 */
	public Servicio setId(int id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the nombre
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 * @return 
	 */
	public Servicio setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 * @return 
	 */
	public Servicio setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	/**
	 * @return the reserva_en_linea
	 */
	public boolean isReserva_en_linea() {
		return reserva_en_linea;
	}

	/**
	 * @param reserva_en_linea the reserva_en_linea to set
	 * @return 
	 */
	public Servicio setReserva_en_linea(boolean reserva_en_linea) {
		this.reserva_en_linea = reserva_en_linea;
		return this;
	}

	/**
	 * @return the link_reserva
	 */
	public String getLink_reserva() {
		return link_reserva;
	}

	/**
	 * @param link_reserva the link_reserva to set
	 * @return 
	 */
	public Servicio setLink_reserva(String link_reserva) {
		this.link_reserva = link_reserva;
		return this;
	}

	/**
	 * @return the cliente_id
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente_id the cliente_id to set
	 * @return 
	 */
	public Servicio setCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}
	
}
