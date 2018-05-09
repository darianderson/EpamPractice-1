package ua.nure.kolesnikov.module9.Containers;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadXML {
	
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("data.properties"));
		
		
		props.loadFromXML(new FileInputStream("data.xml"));
		System.out.println(props);
	}

}
