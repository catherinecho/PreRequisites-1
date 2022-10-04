import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JackBTester {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path a = Paths.get("junit.txt");
        try {
            Files.writeString(a, "JackB Test", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Path p1 = Paths.get("junit1.txt");
        try {
            Files.writeString(p1, "junit1", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Path p2 = Paths.get("junit2.txt");
        try {
            Files.writeString(p2, "junit2", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
		File folder = new File ("objects"); 
		folder.mkdir(); 


		//for blob
		Path p = Paths.get("something.txt");
		try {
			Files.writeString(p, "some content", StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//for index 
		File foo = new File ("foo.txt"); 
		PrintWriter foow = new PrintWriter(foo); 
		foow.append("foo content"); 
		foow.close(); 

		File bar = new File ("bar.txt"); 
		PrintWriter barw = new PrintWriter(bar); 
		barw.append("bar content"); 
		barw.close(); 


		//TEST 1
		ArrayList<String> list = new ArrayList<String>(); 
		list.add("f : oo"); 
		list.add("b : ar"); 
		list.add("foo : bar"); 

		//TEST 2
		ArrayList<String> alisted = new ArrayList<String>();
		alisted.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		alisted.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		alisted.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		alisted.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		alisted.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");



	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File bf = new File ("something.txt"); 
		bf.delete(); 
		File bf2 = new File ("objects/94e66df8cd09d410c62d9e0dc59d3a884e458e05.txt"); 
		bf2.delete(); 

		//delete index file 
		File if1 = new File ("index.txt"); 
		if1.delete(); 

		//delete objects with contents inside
		File if2 = new File ("objects"); 
		//deleteDir(if2); 


		//delete inputs for index 
		File if3 = new File ("foo.txt"); 
		if3.delete(); 
		File if4 = new File ("bar.txt"); 
		if4.delete(); 

		File file= new File("junit.txt");
		file.delete();
		
		File Indexfile=new File("index");
		Indexfile.delete();
		
		File Objectfile=new File("objects");
		Objectfile.delete();
	}
	

	@Test
	void testIndex() throws IOException {
				
		Index ind= new Index();
		ind.init();
		File file=new File("objects/index.txt");
		assertTrue(file.exists());
				
		Path path=Paths.get("objects");
		assertTrue(Files.exists(path));
	}
	
	@Test
	void testBlob() throws IOException, NoSuchAlgorithmException {		
		Blob b=new Blob("junit.txt");
		
		File file=new File("objects/6b6ea6dafb08754e3065aeb0250f792a3a677451");// PUT IN SHA1 STRING
		assertTrue(file.exists());// this is returning false bc file never gets created
	}
	
	@Test
	void testGetSha() throws NoSuchAlgorithmException, IOException {
		Blob b=new Blob("junit.txt");
		assertTrue(b.getSha().equals("6b6ea6dafb08754e3065aeb0250f792a3a677451"));
		
	}
	
	@Test
	void testAdd() throws IOException, NoSuchAlgorithmException {

		Index i = new Index(); 
		i.init(); 
		i.addBlob("foo.txt");
		File i1 = new File ("objects/ec097bb2a51eb70410d13bbe94ef0319680accb6"); 
		assertTrue(i1.exists());
		i.addBlob("bar.txt"); 
		File i2 = new File ("objects/af727e4deee39aead170e830c61b9c2844a3d75b"); 
		assertTrue(i2.exists()); 

		//fails with add

	}

	
	@Test
	//THIS ISN'T CORRECT
	void testDeleteBlob() throws NoSuchAlgorithmException, IOException {
		
		
		//check if file doesn't exist
		File f=new File("objects/f85d527604444aa350aa09dfe93baefbd88f804c");
		Index ind=new Index();		
		assertTrue(!f.exists());
		//deletes junit
		ind.deleteBlob("junit.txt");
		//check if removes line from index
		try (BufferedReader br = new BufferedReader(new FileReader("index"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if (line.equals("junit.txt:f85d527604444aa350aa09dfe93baefbd88f804c")) {
		    	   assertTrue(false);
		       }
		    }
		    assertTrue(true);
		}
		
		
	}

}
