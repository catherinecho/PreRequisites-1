



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Commit {
	static String parent = null;
	static String child  = null;
	static String summary;
	static String author;
	static String date;
	static String psha1 = "";
	static String csha1 = "";
	static String fsha1 = "";
	String pTree = null;
	
	
	public Commit (String sum, String aut, String p) throws NoSuchAlgorithmException, IOException {
		
		Date d=  new Date ();
		date= d.toString();
		File pfile = null; 
		child = null;
		summary = sum;
		author = aut;
		fsha1 = encrypt(summary + date  + author  + parent);
		
		if (p !=null) {
			pfile = new File("objects/" + p);
			PrintWriter out = new PrintWriter(new FileWriter(pfile));
			parent = p;
			BufferedReader in = new BufferedReader(new FileReader(pfile));
			String updated = ""; 
			updated += in.readLine() + "\n"; 
			updated += in.readLine() + "\n"; 
			in.readLine(); 
			updated += "objects/" + fsha1 + "\n"; 
			out.append(updated);
			updated = ""; 
			updated += in.readLine() + "\n"; 
			updated += in.readLine() + "\n"; 
			updated += in.readLine() + "\n";
			out.append(updated);
			out.close();
			in.close();
		}
		TreeObject tree = new TreeObject(getBlobs());
		pTree= tree.getSha1();
		writeFile(); 
		clearOutIndex(); 
		
		
		
		
		
	}
	
	public static void clearOutIndex() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("objects/indext.txt");
		out.print("");
		out.close(); 
	}
	public static ArrayList<String> getBlobs() throws FileNotFoundException, IOException{
		String l = ""; 
		ArrayList<String> blobsNames = new ArrayList<String>(); 
		BufferedReader in = new BufferedReader(new FileReader("index.txt"));
		
		while(l !=null && in.ready()){
			l = in.readLine();
			if(l ==null)
				break;
			blobsNames.add(l);
		}
		//blobsNames.add("tree :" + parent.getPTree());
		in.close();
		
		return blobsNames;
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
        return new BigInteger (1, md.digest()).toString(16);
	}
	
	public String getpTree() {
		return pTree; 
	}
	/*
	public void writeP() throws NoSuchAlgorithmException, IOException {
		parent.setFirst(this);
		parent.writeFile();
	}
	*/
	
			
	public void writeFile() throws IOException, NoSuchAlgorithmException {
		//ArrayList<String> a = con();
		
		//String SHA = encrypt(st);
		File file = new File("objects/" + fsha1);

		FileWriter files = new FileWriter(file);
		BufferedWriter buffer = new BufferedWriter(files);
		buffer.write("objects/" + pTree);
		buffer.newLine();
		if(parent != null) {
			buffer.write(parent);
			buffer.newLine();
		}else {
			buffer.newLine();
		}
		
		//buffer.write(csha1);
		buffer.newLine();
		buffer.write(author);
		buffer.newLine();
		buffer.write(date);
		buffer.newLine();
		buffer.write(summary);
		buffer.close();
		files.close();
	}
	/*
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
	*/	
		
	public String getPTree() {
		return pTree;
	} 
		
	
		
	
}
