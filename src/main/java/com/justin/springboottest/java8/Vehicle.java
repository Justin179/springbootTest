/**
 * 
 */
package com.justin.springboottest.java8;

/**
 * @author EazyBytes
 *
 */
public interface Vehicle {

	public int getSpeed();

	public void applyBreak();

	public default void autoPilot() {
		System.out.println("I will help in driving with out manual support");
	}
	
	public static void sayHello() {
		System.out.println("Hi, This is your favourite car");
	}
	// 介面可以有多個default methods
	public default void autoPilot2() {
		System.out.println("I will help in driving with out manual support2");
	}
}
