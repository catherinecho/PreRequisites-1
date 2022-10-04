import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CommitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		File f = new File("objects/tree.txt"); 
		// make tree.txt and put some content in it
		//assertTrue(f.exists());
		Commit c = new Commit("sum1","auth1", null);	
//		Commit child = new Commit("tree1.txt","sum2","auth2",null);
		Commit child = new Commit("sum2","auth2" ,c.getFSha1());
		Commit child2 = new Commit("sum3","auth2",child.getFSha1());
	}

}
