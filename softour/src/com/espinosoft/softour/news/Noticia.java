/**
 * 
 */
package com.espinosoft.softour.news;

import android.content.ContentValues;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Ibrael
 * 
 */
@DatabaseTable(tableName = "noticia")
public class Noticia {

	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField()
	private String photo;
	
	@DatabaseField(index = true, canBeNull = false)
	private String titulo;
	
	@DatabaseField(canBeNull = false)
	private String link;
	
	@DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING)
	private String descripcion;
	
	@DatabaseField
	private String guid;
	
	@DatabaseField(canBeNull = false)
	private String fecha;

	public Noticia() {

	}

	public Noticia(ContentValues value) {
		titulo = value.getAsString("titulo");
		descripcion = value.getAsString("descripcion");
		fecha = value.getAsString("fecha");
		link = value.getAsString("link");
		photo = value.getAsString("photo");
		id = value.getAsInteger("guid");
	}

	public Noticia(int id, String photo, String titulo, String link,
			String descripcion, String guid, String fecha) {
		this.id = id;
		this.photo = photo;
		this.titulo = titulo;
		this.link = link;
		this.descripcion = descripcion;
		this.guid = guid;
		this.fecha = fecha;
		
		
	}

	public boolean isNew() {
		return this.id > -1;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo == null ? "No photo" : photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getLink() {
		return link;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getGuid() {
		return guid;
	}

	public String getFecha() {
		return fecha;
	}

	public void setTitulo(String t) {
		titulo = t;
	}

	public void setLink(String l) {
		link = l;
	}

	public void setDescripcion(String d) {
		descripcion = d;
	}

	public void setGuid(String g) {
		guid = g;
	}

	public void setFecha(String f) {
		fecha = f;
	}

}
