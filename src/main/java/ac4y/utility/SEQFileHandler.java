package ac4y.utility;

import	java.net.*;
import	java.util.*;

import	java.io.*;

public class SEQFileHandler {
    
	public void write (
		String	aFileName
		,String	aText ) throws IOException {

		FileOutputStream fileOutputStream = null;
		
		fileOutputStream = new FileOutputStream ( aFileName );
	
    	fileOutputStream.write ( aText.getBytes() );
    	
		fileOutputStream.close();
			
	} // write

	public void write (
			String	aFileName
			,String	aText 
			,String aCharset) throws IOException {

       	Writer vOut = 
       		new BufferedWriter(new OutputStreamWriter(
       		    new FileOutputStream(aFileName), aCharset)
       		);
	       
       	vOut.write(aText);
       	
	    vOut.close();
	        	
	} // write

	public void append (
			String	aFileName
			,String	aText ) throws IOException {

		FileOutputStream 	fileOutputStream = new FileOutputStream ( aFileName, true );
	
    	fileOutputStream.write ( aText.getBytes() );
		fileOutputStream.close();
				
	} // append

	public void delete(String aFileName) {

       	File file = new File(aFileName);
	        	
   		file.delete();
				
	} // append

	public void putFile(String aDirectory, String aFileName, String aText) throws IOException {

		createDirectory(aDirectory);
		
    	write(aDirectory + "/"+ aFileName, aText);
    	
    } // putFile

	public String getFromFile (String aFileName) throws IOException {

       	String 	vRecord = new String();
       	String	vResult = new String();
/*
		FileReader 		vFileReader		= new FileReader 	( aFileName );
       	BufferedReader 	vBufferedReader	= new BufferedReader( vFileReader );
*/
       	File fileDir = new File(aFileName);

		BufferedReader vBufferedReader = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));

		String str;       	
       	
       	while ( ( vRecord = vBufferedReader.readLine()) != null) {
       		vResult = vResult + "" + vRecord + "\r\n";
       		//vResult+=vRecord;
       		
		} // while

        return vResult;
        
	} // getFromFile

   	public boolean createDirectory (String aName)
  	{

		boolean	vOk = false;
    
		vOk = ( new File ( aName ) ).mkdirs();

//		vOk = false;
    		
		return vOk;
  	
  	} // createDirectory

   	public void copyfile(String srFile, String dtFile) throws IOException{

   	  File f1 = new File(srFile);
   	  File f2 = new File(dtFile);
   	  InputStream in = new FileInputStream(f1);
   	  
   	  OutputStream out = new FileOutputStream(f2);

   	  byte[] buf = new byte[1024];
   	  int len;
   	  while ((len = in.read(buf)) > 0){
   	  out.write(buf, 0, len);
   	  }
   	  
   	  in.close();
   	  out.close();
   	  
   	  System.out.println("File copied.");

   	}

   	public boolean isExists(String aFileName){

   	   	File file = new File(aFileName);
   	   	return (file.exists() && !file.isDirectory());
   	   	
   	} // isExists

} // SEQFileHandler