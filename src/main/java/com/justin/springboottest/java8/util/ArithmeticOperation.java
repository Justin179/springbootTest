/**
 * 
 */
package com.justin.springboottest.java8.util;

/**
 * @author EazyBytes
 *
 */
@FunctionalInterface
public interface ArithmeticOperation {

	public int performOperation(int a, int b);

	public default void performAdd(int a, int b) {
		System.out.println(a + b);
	}

	private int addTwoSum(int a, int b){
		return a+b;
	}

	public static void printTheInput(int res) {
		System.out.println(res);
	}

}
