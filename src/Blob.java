import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob {
	//Path filePathToWrite = Paths.get (fileName);
	//try {
	//Files.writeString (filePathToWrite, stringToWrite, StandardCharSets.ISO_8859_1);
	//catch (IOException exception
	//System.out.println("Write failed for " + fileName);
	//}	
	private String SHA;
	private String s;
	private String st;
	private String FI;
	
	public Blob(String s) throws IOException, NoSuchAlgorithmException {
		// create new file by telling it where to look "path class" Write to that Path
		this.s = s;
		String string = s;
		File f = new File(s);

		String bReader = "";
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		while (br.ready()) {
			bReader = bReader + (char)br.read();
		}
		st = bReader;
		String SHA1 = encryptThisString(bReader);
		SHA = SHA1;
		PrintWriter out = new PrintWriter (new FileWriter("./objects/"+ SHA));
		out.write(st);
		out.close();
		br.close();
		
		
		
	}
	/*
	private void makeNEWFile(String s) throws IOException {
		Path newFilePath = Paths.get(s);
	    Files.createFile(newFilePath);
	}
	*/
	
	public String getSha () {
		return SHA;
	}
	
	public static String encryptThisString(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(input.getBytes("UTF-8"));
            return new BigInteger (1, md.digest()).toString(16);
    }
	/*
	public void newFile() throws IOException{
		Path pathy = Paths.get("Objects/" + SHA + ".txt");
		try {
			Files.writeString(pathy, st, StandardCharsets.ISO_8859_1);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	*/
	
}