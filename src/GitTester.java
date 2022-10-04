import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.*;
import java.security.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

class GitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path p1 = Paths.get("test1.txt");
        try {
            Files.writeString(p1, "test 1", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p2 = Paths.get("test2.txt");
        try {
            Files.writeString(p2, "test 2", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p3 = Paths.get("test3.txt");
        try {
            Files.writeString(p3, "test 3", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p4 = Paths.get("test4.txt");
        try {
            Files.writeString(p4, "test 4", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p5 = Paths.get("test5.txt");
        try {
            Files.writeString(p5, "test 5", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p6 = Paths.get("test6.txt");
        try {
            Files.writeString(p6, "test 6", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p7 = Paths.get("test7.txt");
        try {
            Files.writeString(p7, "test 7", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testAddCommit() throws NoSuchAlgorithmException, IOException {
		Index i = new Index();
		i.init();
		
		i.addBlob("test1.txt");
		Commit first = new Commit("first commit", "Matthew Chang", null);
		
		i.addBlob("test2.txt");
		i.addBlob("test3.txt");
		
		Commit second = new Commit("second commit", "Benjiman Ham", first.getFSha1());
		//first.setChild(first.getCommitName(), second.getCommitName());
		/*
		i.addBlob("test4.txt");
		i.addBlob("test5.txt");
		Commit third = new Commit("third commit", "Ethan Wang", second.getFSha1());
		//second.setChild(second.getCommitName(), third.getCommitName());
		
		i.addBlob("test6.txt");
		i.addBlob("test7.txt");
		Commit fourth = new Commit("fourth commit", "Gnahc Wehttam", third.getFSha1());
		//third.setChild(third.getCommitName(), fourth.getCommitName());
		*/
	}

}