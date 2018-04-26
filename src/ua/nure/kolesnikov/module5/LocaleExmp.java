package ua.nure.kolesnikov.module5;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleExmp {
	public static void main(String[] args) throws MalformedURLException {
		Locale locale = new Locale("ru", "UA", "Kharkov");
		System.out.println(locale);
		Locale.setDefault(new Locale("ru"));
		ResourceBundle rb = 
			ResourceBundle.getBundle("messages", locale);
		
		System.out.println(rb.getString("hanger"));
	}
}