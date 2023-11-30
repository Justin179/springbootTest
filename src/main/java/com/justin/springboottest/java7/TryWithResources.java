/**
 * 
 */
package com.justin.springboottest.java7;

import com.justin.springboottest.java7.util.CustomResource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author EazyBytes
 * till java 6, try should be followed by either catch or finally block, but from Java 7 we can only
 * try with resource block without catch & finally blocks
 */
public class TryWithResources {
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		beforeJava7();
		withJava7();
		withCustomResInJava7();
	}

	/**
	 * Sample implementation before Java 7 
	 * @throws IOException 
	 */
	public static void beforeJava7() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("D:/eazybytes.txt"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} finally {
			br.close();
		}
	}
	
	/**
	 * Sample implementation from Java 7 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void withJava7() throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("D:/eazybytes.txt"));) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		}
	}
	
	/**
	 * Sample implementation from Java 7 
	 * @throws Exception 
	 */
	public static void withCustomResInJava7() throws Exception {
		try(CustomResource cr = new CustomResource();) {
			cr.readFromResource();
		}
	}

}
