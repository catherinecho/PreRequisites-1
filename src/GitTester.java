import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.*;
import java.security.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

class Gitblober {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path p1 = Paths.get("blob1.txt");
        try {
            Files.writeString(p1, "blob 1", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p2 = Paths.get("blob2.txt");
        try {
            Files.writeString(p2, "blob 2", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p3 = Paths.get("blob3.txt");
        try {
            Files.writeString(p3, "blob 3", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p4 = Paths.get("blob4.txt");
        try {
            Files.writeString(p4, "blob 4", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p5 = Paths.get("blob5.txt");
        try {
            Files.writeString(p5, "blob 5", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p6 = Paths.get("blob6.txt");
        try {
            Files.writeString(p6, "blob 6", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Path p7 = Paths.get("blob7.txt");
        try {
            Files.writeString(p7, "blob 7", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void blobAddCommit() throws NoSuchAlgorithmException, IOException {
		Index i = new Index();
		i.init();
		
		i.addBlob("blob1.txt");
		Commit first = new Commit("first commit", "Catherine Cho", null);
		
		i.addBlob("blob2.txt");
		i.addBlob("blob3.txt");
		
		Commit second = new Commit("second commit", "Matthew Chang", first.getFSha1());
		//first.setChild(first.getCommitName(), second.getCommitName());
		/*
		i.addBlob("blob4.txt");
		i.addBlob("blob5.txt");
		Commit third = new Commit("third commit", "Matthew Ko", second.getFSha1());
		//second.setChild(second.getCommitName(), third.getCommitName());
		
		i.addBlob("blob6.txt");
		i.addBlob("blob7.txt");
		Commit fourth = new Commit("fourth commit", "Zoe Shin", third.getFSha1());
		//third.setChild(third.getCommitName(), fourth.getCommitName());
		*/
	}

}