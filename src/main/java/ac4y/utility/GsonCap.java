package ac4y.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCap {
	
	private transient int field = 2;
	
	public Gson getGson(){
		
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();      
        //builder.setPrettyPrinting().serializeNulls();
        
        return builder.create();
        
	} // getGson
	
	public String getObjectAsJson(Object aObject){

		return getGson().toJson(aObject);
        
	}

	public Object getFromJson(String aJson, Class aClass){
		
		return getGson().fromJson(aJson, aClass);
		
	}
	
	
} // GsonCap