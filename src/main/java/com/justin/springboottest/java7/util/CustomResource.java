/**
 * 
 */
package com.justin.springboottest.java7.util;

/**
 * @author EazyBytes
 *
 */
public class CustomResource implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println(" From close method inside the CustomResource class");

	}

	public void readFromResource() {
		System.out.println(" Reading data... ");
	}

}
