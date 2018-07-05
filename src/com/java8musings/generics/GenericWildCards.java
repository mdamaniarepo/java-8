package com.java8musings.generics;

/**
 * 
 * Wildcard is what object is for a raw type
 * 
 * ? denotes that it is an unknown type
 * 
 * @author mdama1
 *
 */
public class GenericWildCards {

	public static void main(String[] args) {
		LookupHandler handler = new LookupHandler();

		Lookup<Object> objectLookup = new Lookup<Object>(new Object());
		handler.handleLookup(objectLookup);

		// Lookup<String> stringLookup = new Lookup<String>("hello");
		// handler.handleLookup(stringLookup); // this will result in compile time error

		// new Lookup<?>() is not allowed as you cannot create an object of unknown type

		Lookup<?> unknownLookup = new Lookup<String>("hello"); // this is valid
		// String s = unknownLookup.get(); // not allowed
		Object s = unknownLookup.get();
		System.out.println(s);

		// upper bound in generics

	}
	
	public static double sum(Wrapper<? extends Number> n1, Wrapper<? extends Number> n2) {
		return n1.get().doubleValue() + n2.get().doubleValue();
	}

	public class Wrapper<T> {
		private T t;

		public Wrapper(T t) {
			this.t = t;
		}

		public T get() {
			return t;
		}

		public void set(T t) {
			this.t = t;
		}
	}

}
