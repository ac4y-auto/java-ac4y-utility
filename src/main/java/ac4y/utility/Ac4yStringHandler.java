package ac4y.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Ac4yStringHandler {

    public String getSimpled ( String a_text ) {

    	String 	accentForm 			= "áíúőűöüóéÁÍÚŐŰÖÜÓÉ";
    	String 	withoutAccentForm 	= "aiuououoeAIUOUOUOE";
    		
    	char	oneChar;
    	String	converted	= "";
    	int		conversionIndex = 0;
    	
		for ( int index = 0; index < a_text.length(); index++ ) {
			
			oneChar = a_text.charAt ( index );
			
			conversionIndex = accentForm.indexOf ( oneChar );
			
			if	( conversionIndex != -1 )
				oneChar = withoutAccentForm.charAt ( conversionIndex );

			if 	(  
					((oneChar >= 'a') && (oneChar <= 'z')) ||
					((oneChar >= '0') && (oneChar <= '9')) ||
					((oneChar >= 'A') && (oneChar <= 'Z'))
			)
				converted = converted + oneChar;
			
		}

		return converted.toUpperCase();

    } // getSimpled
    
    public String getExtended(boolean aNeedExtension, String aBefore, String aAfter, String aBase) {
    	
    	if (aNeedExtension)
    		return aBefore + aBase + aAfter;
    	else
    		return aBase;
    		
    	
    }

    public String getEncoded(String aInput, String aCodePage) {
        
    	String result = "";
    	
    	try {
			result = URLEncoder.encode(aInput, aCodePage);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result;
    	
    } // getEncoded
    
    public String getEncoded(String aInput) {
        
    	return getEncoded(aInput, "UTF-8");
    	
    } // getEncoed
    
    public String getLastPart(String aString, String aSeparator) {
    
    	String [] items = aString.split(aSeparator);
    	
    	if (items.length==0)
    		return aString;
    	else
    		return items[items.length-1];
    	
    } // getLastPart
        
    public String concatSmart(String aFirst, String aSecond, String aSeparator) { 

        if (aFirst!=null)
            return aFirst + aSeparator + aSecond;
        else
            return aSecond;

    } // concatSmart
    
} // StringHandler