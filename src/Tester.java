

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class Tester {
public static void main (String []args) throws IOException, NoSuchAlgorithmException  {
//	String a = "Object";
//	Blob blobby;
//	try {
//		blobby = new Blob (a);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println( Blob.encryptThisString(a));
	Blob blobby = new Blob ("s");
}
}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		Blob b = new Blob ("/Users/meeraburghardt/eclipse-workspace/Git PreRequisites/Object.txt");
	System.out.println(b.encryptThisString("abcdefg"));
		Index i = new Index();
		i.init();
		i.addBlob("/Users/meeraburghardt/eclipse-workspace/Git PreRequisites/Object.txt");
		i.deleteBlob("/Users/meeraburghardt/eclipse-workspace/Git PreRequisites/Object.txt");
	}

}