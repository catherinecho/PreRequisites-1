



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
	static String parent ;
	static String child;
	static String summary;
	static String author;
	static String date;
	static String fsha1 = "";
	static String pTree = null;
	static String parT = ""; 
	
	
	public Commit (String sum, String aut, String p) throws NoSuchAlgorithmException, IOException {
		
		Date d=  new Date ();
		date= d.toString();
		File pfile = null; 
		child = null;
		summary = sum;
		author = aut;
		fsha1 = encrypt(summary + date  + author  + parent);
		parent = p;
		/*
		if(p !=null) {
			setChild(parent, fsha1);
		}
		
		if (p !=null) {
			
		}
		*/
		
		TreeObject tree = new TreeObject(getBlobs());
		pTree= tree.getSha1();
		writeFile(); 
		clearOutIndex(); 
		
		
		
		
		
	}
	/*
	public static void setChild( String p, String c) throws IOException {
		File parentF = new File("objects/" + p);
		PrintWriter out = new PrintWriter(new FileWriter(parentF));
		
		BufferedReader in = new BufferedReader(new FileReader(parentF));
		
		//updated += "objects/" + fsha1 + "\n"; 
		out.append(updated);
		updated = ""; 
		c = in.readLine(); 
		updated += c + "\n"; 
		updated += in.readLine() + "\n"; 
		updated += in.readLine() + "\n";
		out.append(updated);
		out.close();
		in.close();
	}
	*/
	public void setChild(Commit c) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(new File("objects/" + parent)));
		child = c.getFSha1();
		String updated = ""; 
		updated += in.readLine() + "\n"; 
		updated += in.readLine() + "\n"; 
		in.readLine(); 
		String updated2 = "";
		updated2+= in.readLine() + "\n"; 
		updated2+= in.readLine() + "\n"; 
		updated2 += in.readLine() + "\n"; 
		PrintWriter pw = new PrintWriter(new FileWriter("objects/" + parent));
		pw.append(updated + "objects/" + child + "\n" + updated2);
		pw.close();
	}
	public static void clearOutIndex() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("objects/index.txt");
		out.print("");
		out.close(); 
	}
	public static ArrayList<String> getBlobs() throws FileNotFoundException, IOException{
		ArrayList<String> blobsNames = new ArrayList<String>(); 
		//ArrayList<String> list = new ArrayList<String>();
		File f = new File("objects/" + parent);
		if (parent != null) {
			Scanner input = new Scanner(f);
			String line = input.nextLine();
			blobsNames.add("tree : " + line);
			input.close();
		}
		String l = ""; 
		boolean check = false;
		
		ArrayList<String> de = new ArrayList<String>(); 
		BufferedReader in = new BufferedReader(new FileReader("objects/index.txt"));
		
		while(l !=null && in.ready()){
			
			l = in.readLine();
			if(l ==null)
				break;
			String n = "blob : " + l.substring(l.indexOf(':') + 2) + " " + l.substring(0,l.indexOf(':'));
			blobsNames.add(n);
		}	
		
		//blobsNames.add("tree :" + parent.getPTree());
		in.close();
		/*
		if (!check && parent != null) {
			File f=  new File ("objects/" + parent);
			BufferedReader in2 = new BufferedReader(new FileReader(f)); 
			String tmp = in.readLine();
			blobsNames.add("tree : " + tmp.substring(8));
			in2.close();
		}
		else if(check) {
			File f = new File(parT);
			BufferedReader in2 = new BufferedReader(new FileReader(f));
			String prev = "";
			while (true) {
				String next = in.readLine();
				if(next == null)
					break;
				if (next.contains("tree :")) prev = next;
				else if (de.size()==0) blobsNames.add(next);
				
			}
			
			
			
		}
		*/
		
		return blobsNames;
	}
	public String getFSha1() {
		return fsha1; 
	}
	
	public void deleteEdit(ArrayList<String> de) {
		
		
		
		
	}
	public static String encrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        
        // getInstance() method is called with algorithm SHA-1
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.reset();
        md.update(input.getBytes("UTF-8"));
        return new BigInteger (1, md.digest()).toString(16);
	}
	
	/*
	public void writeP() throws NoSuchAlgorithmException, IOException {
		parent.setFirst(this);
		parent.writeFile();
	}
	*/
	
	public void writeFile() throws NoSuchAlgorithmException, IOException {
		File f = new File(fsha1);
		PrintWriter p = new PrintWriter("objects/"+ f);
		p.append("objects/" + pTree + "\n");
		if (parent == null) p.append("\n");		
		else p.append("objects/" + parent + "\n");
			
		if (child == null) p.append("\n");
		else p.append("objects/" + child + "\n");
			
		p.append(author + "\n");
		p.append(date + "\n");
		p.append(summary + "\n");
		p.close();
	}
	
	public String getPTree() {
		return pTree;
	} 
		
	
		
	
}
