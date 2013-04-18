package com.espinosoft.softour.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "clientes")
public class Cliente extends BaseDaoEnabled{

	@DatabaseField(generatedId= true)
	private int id;
	
	@DatabaseField(canBeNull = false)
	private boolean owner;
	
	@DatabaseField
	private String direccion;
	
	@DatabaseField
	private String telefono;
	
	@ForeignCollectionField
	private ForeignCollection<Servicio> servicios;
	
	@ForeignCollectionField
	private ForeignCollection<Actividad> actividades;

	@ForeignCollectionField(foreignFieldName = "chefPartenaire")
	private ForeignCollection<Cliente> terceros;
	
	@DatabaseField(foreign = true)
	private Cliente chefPartenaire;

	/**
	 * @return the chefPartenaire
	 */
	public Cliente getChefPartenaire() {
		return chefPartenaire;
	}

	/**
	 * @param chefPartenaire the chefPartenaire to set
	 */
	public void setChefPartenaire(Cliente chefPartenaire) {
		this.chefPartenaire = chefPartenaire;
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
	 * @return the owner
	 */
	public boolean isOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the servicios
	 */
	public ForeignCollection<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(ForeignCollection<Servicio> servicios) {
		this.servicios = servicios;
	}

	/**
	 * @return the actividades
	 */
	public ForeignCollection<Actividad> getActividades() {
		return actividades;
	}

	/**
	 * @param actividades the actividades to set
	 */
	public void setActividades(ForeignCollection<Actividad> actividades) {
		this.actividades = actividades;
	}

	/**
	 * @return the terceros
	 */
	public ForeignCollection<Cliente> getTerceros() {
		return terceros;
	}

	/**
	 * @param terceros the terceros to set
	 */
	public void setTerceros(ForeignCollection<Cliente> terceros) {
		this.terceros = terceros;
	}

	
}
