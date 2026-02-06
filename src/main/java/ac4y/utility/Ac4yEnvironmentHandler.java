package ac4y.utility;

import java.net.URL;
import java.net.URLClassLoader;

public class Ac4yEnvironmentHandler {

	public void showClassPath() {
		
		ClassLoader cl = ClassLoader.getSystemClassLoader();

	    URL[] urls = ((URLClassLoader)cl).getURLs();

	    for(URL url: urls){
	    	System.out.println(url.getFile());
	    }

	}
	
}