package com.java8musings.generics;

public class Lookup<T> {

	private T t;
	
	public Lookup(T t) {
		this.t = t;
	}
	
	public void executeLookup() {
		System.out.println("Performing Lookup: " + t);
	}
	
	public T get() {
		return t;
	}
}
