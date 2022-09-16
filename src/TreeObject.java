import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

public class TreeObject {
	ArrayList<String> arr= new ArrayList<String>();
	String arrayContents="";
	String sha1="";
	
	public TreeObject(ArrayList<String> a) throws IOException, NoSuchAlgorithmException{
		arr=a;
		int len = arr.size();
	      for (int i = 0; i < len; i++) {
	         arrayContents+=arr.get(i);
	         if(i!=len-1) {
	        	 arrayContents+="\n";
	         }
	      }	
	
	//getting sha1
	sha1=generateSHA1(arrayContents);
	
	//creates file and adds array to new file
	add(arr);
	
	}
	
	public static void main (String[]args) throws IOException, NoSuchAlgorithmException {
		ArrayList<String> array= new ArrayList<String>();
		array.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		array.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		array.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		array.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		array.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		TreeObject tree=new TreeObject(array);
		
		
	}
	
	public static String generateSHA1(String st) throws IOException {
		String str=st;
		String SHA="";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(str.getBytes("utf8"));
	        SHA = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return SHA;
		
    }
	
	
	public void add(ArrayList<String> a) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		//create file
		Path p = Paths.get("./objects/"+sha1);
		
		//putting into file
		BufferedWriter bf=null;
		String current="";
		try {
			bf=new BufferedWriter(new FileWriter("./objects/"+sha1));
			int len = arr.size();
		      for (int i = 0; i < len; i++) {
		         current=arr.get(i);		
               bf.write(current); 
               // new line
               bf.newLine();
			}
		}
		catch (IOException e) {
            e.printStackTrace();
        }
        finally { 
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
		
	}
	

}
