package com.espinosoft.softour.news;

import java.util.Date;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "actividad")
public class Actividad {

	@DatabaseField(id=true)
	private int id;
	
	@DatabaseField
	private String titulo;
	
	@DatabaseField
	private Date fecha_inicio;
	
	@DatabaseField(dataType = DataType.LONG_STRING)
	private String descripcion;
	
	@DatabaseField
	private int cliente;
	
	@DatabaseField
	private boolean isRegular;
	
	@DatabaseField
	private boolean isService;
	
	@DatabaseField
	private LatLng point;

	public Actividad() {
		//point = new GeoPoint(arg0, arg1)
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the fecha_inicio
	 */
	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * @param fecha_inicio the fecha_inicio to set
	 */
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the cliente
	 */
	public int getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the isRegular
	 */
	public boolean isRegular() {
		return isRegular;
	}

	/**
	 * @param isRegular the isRegular to set
	 */
	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}

	/**
	 * @return the isService
	 */
	public boolean isService() {
		return isService;
	}

	/**
	 * @param isService the isService to set
	 */
	public void setService(boolean isService) {
		this.isService = isService;
	}

	/**
	 * @return the point
	 */
	public LatLng getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(LatLng point) {
		this.point = point;
	}
	
	
}
