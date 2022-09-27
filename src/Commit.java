



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

public class Commit {
	private static Commit first = null;
	private static Commit parent = null;
	private static String summary;
	private static String author;
	private static String date;
	private static String sha1 = "";
	private String pTree = null;
	
	
	public Commit (String sum, String aut,  String fileName, Commit c) throws NoSuchAlgorithmException, IOException {
		
	
		Date d=  new Date ();
		date= d.toString();
		pTree =  fileName;
		parent = c;
		summary = sum;
		author = aut;
		if (c !=null) {
			parent = c;
			parent.setFirst (this);
			parent.writeFile();
		}
		
	}
		
			public void writeP() throws NoSuchAlgorithmException, IOException {
				parent.setFirst(this);
				parent.writeFile();
			}
			
			public void setFirst (Commit c) {
				first = c;
			}
			public void setParent (Commit c) {
					parent = c;
			}
			
		public void writeFile() throws IOException, NoSuchAlgorithmException {
			String st = "";
			ArrayList<String> arr = Con();
			for (String str : arr) {
				if (!str.equals(null)) {
					st += str;
				}
			}
			
			String SHA = encrypt(st);
			File file = new File(SHA);
	
			FileWriter files = new FileWriter("objects/" + file);
			BufferedWriter buffer = new BufferedWriter(files);
			for (String s : arr) {
				if (!s.equals(null)) {
					buffer.write(s);
				}
				buffer.newLine();
			}
			buffer.close();
			files.close();
		}
		private ArrayList<String> Con() {
			
			ArrayList<String> Strs = new ArrayList<String>();
			
			Strs.add(pTree);
			
			if (parent != null) {
				Strs.add(parent.getPTree());
			} else {
				Strs.add(null);
			}
			if (first != null) {
				Strs.add(parent.getPTree());
			} else {
				Strs.add(null);
			}
			Strs.add(author);
			Strs.add(date);
			Strs.add(summary);
			return Strs;
		}
		
		
		public String getPTree() {
			return pTree;
		} 
		
	
	public static String encrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	
	    
	    // getInstance() method is called with algorithm SHA-1
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    md.reset();
	    md.update(input.getBytes("UTF-8"));
	    sha1 = new BigInteger (1, md.digest()).toString(16);
	    return sha1;
	}
		
	
}
