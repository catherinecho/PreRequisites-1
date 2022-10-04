



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Commit {
	static Commit first = null;
	static Commit parent = null;
	static Commit child  = null;
	static String summary;
	static String author;
	static String date;
	static String psha1 = "";
	static String csha1 = "";
	static String fsha1 = "";
	String pTree = null;
	
	
	public Commit (String sum, String aut, Commit p) throws NoSuchAlgorithmException, IOException {
		
		Date d=  new Date ();
		date= d.toString();
		parent = p;
		child = null;
		summary = sum;
		author = aut;
		
		if (parent !=null) {
			psha1 = "objects/" + encrypt(parent.getFSha1());
		}else
			psha1 = null;
		/*
		String st = "";
		ArrayList<String> arr = con();
		for (String str : arr) {
			System.out.println(arr);
			if (str==null) {
				st += str;
			}
		}
		*/
		fsha1 = encrypt(summary+"\n" + date + "\n" + author + "\n" + psha1);
	
		//sha1 = encrypt(st);
		
	}
	public static String getFSha1() {
		return fsha1; 
	}
	
	public static String getContents(String filename) throws FileNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Scanner in = new Scanner(new FileReader(filename));
		String s= "";
		while(in.hasNextLine()) {
			s+= in.nextLine();
		}
		return s;
		
	}
	public static String encrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	
	    
	    // getInstance() method is called with algorithm SHA-1
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    md.reset();
	    md.update(input.getBytes("UTF-8"));
	    sha1 = new BigInteger (1, md.digest()).toString(16);
	    return sha1;
	}
	
	public String getpTree() {
		return pTree; 
	}
	/*
	private ArrayList<String> contents() {
		ArrayList<String> a = new ArrayList<String>();
		a.add(pTree);
		if (parent == null) 
			a.add(null);
		else 
			a.add(parent.getpTree());
	
		if (child == null) {
			a.add(null);	
		} else {
			a.add(parent.getLocation());
		}
		a.add(author);
		a.add(date);
		a.add(summary);
		return a;
	}
	*/
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
		ArrayList<String> a = con();
		
		//String SHA = encrypt(st);
		File file = new File(sha1);

		FileWriter files = new FileWriter("objects/" + file);
		BufferedWriter buffer = new BufferedWriter(files);
		for (String s : a) {
			if (!s.equals(null)) {
				buffer.write(s);
			}
			buffer.newLine();
		}
		buffer.close();
		files.close();
	}
	private ArrayList<String> con() {
		
		ArrayList<String> Strs = new ArrayList<String>();
		
		Strs.add(pTree);
		
		//read file 
		
		
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
		
	
		
	
}
