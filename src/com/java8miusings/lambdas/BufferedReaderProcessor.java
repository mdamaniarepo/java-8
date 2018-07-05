package com.java8miusings.lambdas;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {
	
	String process(BufferedReader br) throws IOException;

}
