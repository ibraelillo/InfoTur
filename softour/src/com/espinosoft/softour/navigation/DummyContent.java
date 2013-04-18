package com.espinosoft.softour.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.espinosoft.softour.HomeFragment;
import com.espinosoft.softour.ItemDetailFragment;
import com.espinosoft.softour.Locatable;
import com.espinosoft.softour.MapFullFragment;
import com.espinosoft.softour.database.Servicio;
import com.espinosoft.softour.database.ServicioManager;
import com.espinosoft.softour.fragment.NoticiaFragment;
import com.google.android.gms.internal.cl;
import com.google.android.gms.maps.SupportMapFragment;

import android.app.Notification;
import android.support.v4.app.Fragment;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		// Add 3 sample items.
		addItem(new DummyItem("location", HomeFragment.class));
		addItem(new DummyItem("actividades", ItemDetailFragment.class));
		addItem(new DummyItem("map", MapFullFragment.class));
		addItem(new DummyItem("servicios", ItemDetailFragment.class));		
		addItem(new DummyItem("noticias", NoticiaFragment.class));
		addItem(new DummyItem("ofertas", ItemDetailFragment.class));
		addItem(new DummyItem("terceros", ItemDetailFragment.class));
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	
	
	
	
	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public Class<Fragment> content;

		public DummyItem(String id, Class cls) {
			this.id = id;
			this.content = cls;
		}

		public Class<Fragment> getFragment() {
			return content;
		}
	}
}
