package git;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Commit {
private static Commit first = null;
private static Commit parent = null;
private static String summary, author, date;
private static String sha1 = "";
private String pTree = null;


public Commit (String sum, String aut, String dat, String fileName, Commit c) {
	summary = sum;
	author = aut;
	date= dat;
	pTree =  "objects/" + fileName;
	parent = c;
}
	public String getDate() {
		Date d=  new Date ();
		return d.toString();
	}


public static void encryptThisString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    
    // getInstance() method is called with algorithm SHA-1
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    md.reset();
    md.update(input.getBytes("UTF-8"));
    sha1 = new BigInteger (1, md.digest()).toString(16);
}
	
	public String getDate() {
		return date;
	}
	
	
}
