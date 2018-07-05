package com.java8musings.streams;

import java.util.stream.Stream;

public class GeneratingStreamsTest {
	
	public static void main(String[] args) {
		
		// stream from values
		Stream<String> stringStream = Stream.of("Kiaan", "Amyra", "Sasmita", "Mayank");
		stringStream.map(String::toUpperCase).forEach(System.out::println);
		
		// Genearting empty stream
		Stream<String> emptyStream = Stream.empty();
		System.out.println("Empty stream: " + emptyStream);
	}

}
