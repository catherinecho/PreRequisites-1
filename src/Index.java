import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Index {
 private HashMap<String, String> hashy= new HashMap <String, String>();
 private File fi;
 private File ob;
 

	
	public void init() throws IOException  {
		File d = new File("objects");
		d.mkdir();
		Path p = Paths.get("objects");
		File f = new File (p+"/index.txt");
		fi =f;
		f.createNewFile();
		//File d = new File("test/objects"); 
		//d.mkdir(); 
		
	
		
		
	}
	

	public void addBlob (String s) throws IOException, NoSuchAlgorithmException {
		Blob blobby = new Blob (s);
		hashy.put(s,blobby.encryptThisString(s));
//		 File file = new File(outputFilePath);
		  
	    //BufferedWriter bf = null;
	  
	  
	            // create new BufferedWriter for the output file
	    //bf = new BufferedWriter(new FileWriter(fi));
	    PrintWriter bf = new PrintWriter(new FileWriter("objects/index.txt"));
	            // iterate map entry
	  
	                // put key and value separated by a colon
        //bf.write(s + ":"+ blobby.encryptThisString(s));
        //PrintWriter out = new PrintWriter("objects/index");
 		for (String file: hashy.keySet()) 
 			bf.println(file + " : " + hashy.get(file));
 	 	
 		bf.close();
	}
	
	
	public void deleteBlob(String s) throws IOException, NoSuchAlgorithmException {
		hashy.remove(s);
		Blob blobby = new Blob (s);
		File tempFile = new File("myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(fi));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = s + ":" + blobby.encryptThisString(s);
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		boolean successful = tempFile.renameTo(fi);
	
	}
}