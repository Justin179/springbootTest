/**
 * 
 */
package com.justin.springboottest.java8.streams;

import com.justin.springboottest.java8.util.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author EazyBytes
 *
 */
public class StreamOperations {

	// 重看一遍，增加熟悉度，不好理解就影片中找說明

	public static void mapInStreams() {
		List<String> departmentList = new ArrayList<>();
		departmentList.add("Supply");
		departmentList.add("HR");
		departmentList.add("Sales");
		departmentList.add("Marketing");

		departmentList.stream().map(word -> word.toUpperCase()).forEach(word->System.out.println(word));
	}

	// 看這個有助釐清觀念
	public static void flatMapInStreams() {
		String[] arrayOfWords = { "Eazy", "Bytes" };
		Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
		/*
		 * streamOfwords.map(word ->
		 * word.split("")).flatMap(Arrays::stream).forEach(System.out::println);
		 */
		Stream<String[]> streamOfLetters = streamOfwords.map(word ->word.split(""));
//		streamOfLetters.flatMap(Arrays::stream).forEach(System.out::println);

		  List<List<String>> list = Arrays.asList( Arrays.asList("Eazy"), Arrays.asList("Bytes"));
		  System.out.println(list); // [[Eazy], [Bytes]]

//		  list.stream().map(Collection::stream).forEach(System.out::println);
		  list.stream().flatMap(Collection::stream).forEach(System.out::println); // 這行可以確認觀念
	}

	public static void filterInStreams() {
		List<String> departmentList = new ArrayList<>();
		departmentList.add("Supply");
		departmentList.add("HR");
		departmentList.add("Sales");
		departmentList.add("Marketing");

		departmentList.stream().filter(word -> word.startsWith("S")).forEach(System.out::println);
	}

	public static void traverseOnceInStreams() {
		try {
			List<String> departmentList = new ArrayList<>();
			departmentList.add("Supply");
			departmentList.add("HR");
			departmentList.add("Sales");
			departmentList.add("Marketing");
			Stream<String> depStream = departmentList.stream();

			depStream.forEach(System.out::println);
			depStream.forEach(System.out::println);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	// 產生10個int隨機數
	public static void limitInStreams() {
		Stream.generate(new Random()::nextInt).limit(10).forEach(System.out::println);
	}
	// 從11開始，重覆+1這個行為20次，一直到30為止
	public static void skipInStreams() {
		Stream.iterate(1, n -> n + 1).skip(10).limit(20).forEach(System.out::println);
	}
	// 1+2+3…一路加到20
	public static void reduceInStreams() {
		System.out.println(Stream.iterate(1, n -> n + 1).limit(20).reduce(0, (a, b) -> a + b));
	}


	public static void maxInStreams() {
		System.out.println(Stream.iterate(1, n -> n + 1).limit(20).max((a, b) -> a + b));
	}

	public static void collectStreams() {
		List<String> departmentList = new ArrayList<>();
		departmentList.add("Supply");
		departmentList.add("HR");
		departmentList.add("Sales");
		departmentList.add("Marketing");
		Stream<String> depStream = departmentList.stream();

		List<String> newDepartmentList = depStream.filter(word -> word.startsWith("S")).collect(Collectors.toList());
		newDepartmentList.forEach(System.out::println);
	}



	public static void collectingAndThenStreams() {
		// list內有7個Products
		List<Product> productList = Arrays.asList(new Product("Apple", 1200),
				new Product("Samsung", 1000), new Product("Nokia", 800),
				new Product("BlackBerry", 1000), new Product("Apple Pro Max", 1500),
				new Product("Mi", 800), new Product("OnePlus", 1000));

		String maxPriceProduct = productList.stream()
				.collect(
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Product::getPrice)),
														(Optional<Product> product)-> product.isPresent()? product.get().getName():"None")
				);
		System.out.println("The product with max price tag is: " + maxPriceProduct);
	}




	public static void groupingByStreams() {
		// list內有7個Products
		List<Product> productList = Arrays.asList(new Product("Apple", 1200), new Product("Samsung", 1000),
				new Product("Nokia", 800), new Product("BlackBerry", 1000), new Product("Apple Pro Max", 1500),
				new Product("Mi", 800), new Product("OnePlus", 1000));

		Map<Integer, List<Product>> groupByPriceMap = productList.stream().collect(Collectors.groupingBy(Product::getPrice));
		System.out.println("The list of products grouped by price is: " + groupByPriceMap); // price:list (以價格分群)
	}



	public static void partitioningByStreams() {
		List<Product> productList = Arrays.asList(new Product("Apple", 1200), new Product("Samsung", 1000),
				new Product("Nokia", 800), new Product("BlackBerry", 1000), new Product("Apple Pro Max", 1500),
				new Product("Mi", 800), new Product("OnePlus", 1000));

		Map<Boolean, List<Product>> costlyProducts = productList.stream()
				.collect(Collectors.partitioningBy(product -> product.getPrice() > 1000));
		System.out.println("The list of products partitioned by price is: " + costlyProducts);
	}



	public static void parallelStreams() {
		List<String> departmentList = new ArrayList<>();
		departmentList.add("Supply");
		departmentList.add("HR");
		departmentList.add("Sales");
		departmentList.add("Marketing");
		departmentList.add("Insurance");
		departmentList.add("Security");
		departmentList.add("Finance");

		departmentList.parallelStream().forEach(System.out::println);

	}

	public static void main(String[] args) {streamPipeline();}

	private static void streamPipeline() {
		List<Integer> inputNums = new ArrayList<>();
		inputNums.add(5);
		inputNums.add(2);
		inputNums.add(11);
		inputNums.add(7);
		inputNums.add(4);
		inputNums.add(13);
		inputNums.add(9);

		List<Integer> newNums = inputNums.stream().filter(num->num%2!=0).map(num-> num*num).sorted().collect(Collectors.toList());
		newNums.forEach(System.out::println);
	}


}
