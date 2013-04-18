package com.espinosoft.softour.news;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.espinosoft.softour.database.NoticiaManager;

import android.util.Log;


public class Parser {

	private URL url;
	private Document document;
	NoticiaManager manager;

	public Parser(URL url) {
		this.url = url;
	}

	public Parser(String spec) throws MalformedURLException {
		this.url = new URL(spec);
	}

	private String obtenerTexto(Node dato) {
		StringBuilder texto = new StringBuilder();
		NodeList fragmentos = dato.getChildNodes();
		for (int k = 0; k < fragmentos.getLength(); k++) {
			texto.append(fragmentos.item(k).getNodeValue());
		}
		return texto.toString();
	}

	/**
	 * Abre la coneexion con la url especificada
	 * 
	 * @return
	 */
	private void Read() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(url.openConnection().getInputStream());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Parser de documento xml con dom
	 * 
	 * @return
	 */
	public List<Noticia> Parse() {
		List<Noticia> noticias = new ArrayList<Noticia>();	
		if(document == null)
			Read();

		try {
			// Nos posicionamos en el nodo principal del árbol (<rss>)
			Element root = document.getDocumentElement();
			
			// Localizamos todos los elementos <item>
			NodeList items = root.getElementsByTagName("item");
			
			// Recorremos la lista de noticias
			for (int i = 0; i < items.getLength(); i++) {
				Noticia noticia = new Noticia();
				
				// Obtenemos la noticia actual
				Node item = items.item(i);
				
				// Obtenemos la lista de datos de la noticia actual
				NodeList datosNoticia = item.getChildNodes();
				
				// Procesamos cada dato de la noticia
				for (int j = 0; j < datosNoticia.getLength(); j++) {
					Node dato = datosNoticia.item(j);
					String etiqueta = dato.getNodeName();
					if (etiqueta.equals("title")) {
						String texto = obtenerTexto(dato);
						noticia.setTitulo(texto);
					} else if (etiqueta.equals("link")) {
						noticia.setLink(dato.getFirstChild().getNodeValue());
					} else if (etiqueta.equals("image")) {
						String texto = obtenerTexto(dato);
						noticia.setPhoto(texto);
					}else if (etiqueta.equals("description")) {
						String texto = obtenerTexto(dato);
						noticia.setDescripcion(texto);
					} else if (etiqueta.equals("guid")) {
						noticia.setGuid(dato.getFirstChild().getNodeValue());
					} else if (etiqueta.equals("pubDate")) {
						noticia.setFecha(dato.getFirstChild().getNodeValue());
					}
				}
				noticias.add(noticia);				
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}		
		
		return noticias;
	}

}
