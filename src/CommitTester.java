import static org.junit.jupiter.api.Assertions.*;

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
		Commit c = new Commit("sum1","auth1","tree.txt", null);	
//		Commit child = new Commit("tree1.txt","sum2","auth2",null);
	}

}