package com.espinosoft.softour.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DataBaseConfigUtil extends OrmLiteConfigUtil {

	public DataBaseConfigUtil() {
		
	}
	
	public static void main(String[] args) throws Exception {
		writeConfigFile("ormlite_config.txt");
	}

}
