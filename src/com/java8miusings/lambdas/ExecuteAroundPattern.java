package com.java8miusings.lambdas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Execute around pattern consists of 3 phases: Setup, process and cleanup
 * phase.
 * 
 * @author mdama1
 *
 */
public class ExecuteAroundPattern {

	public static void main(String[] args) {
		ExecuteAroundPattern eap = new ExecuteAroundPattern();
		String content = eap.readFile("D:\\Scala\\scala-workspace\\Java8\\bin\\someFile.txt");
		System.out.println("Content: " + content);

		content = eap.readFile("D:\\Scala\\scala-workspace\\Java8\\bin\\someFile.txt",
				(BufferedReader br) -> br.readLine());
		System.out.println("Content: " + content);

		content = eap.readFile("D:\\Scala\\scala-workspace\\Java8\\bin\\someFile.txt",
				(BufferedReader br) -> br.readLine() + " " + br.readLine());
		System.out.println("Content: " + content);

	}

	public String readFile(String fileName) {
		String content = null;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
			content = br.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public String readFile(String fileName, BufferedReaderProcessor brp) {
		String content = null;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
			content = brp.process(br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
