package com.java8musings.generics;

/**
 * Here the generic type is mentioned in the triangle brackets where T is type,
 * V is value, K is key and E is element
 * 
 * The type parameter value could be specified when you declare a variable of
 * the generic type and create an object of your generic type
 * 
 * You can use type parameters in class code instance variables, constructors
 * methods etc
 * 
 * The parameter that is specified in the type declaration is called a formal
 * type parameter; for example, T is a formal type parameter in the
 * GenericActionHandler<T> class declaration
 * 
 * When you replace the formal type parameter with the actual type (e.g. in
 * Wrapper<String> you replace the formal type parameter T with String), it is
 * called a parameterized type.
 * 
 * A reference type in Java, which accepts one or more type parameters, is
 * called a generic type.
 * 
 * A generic type is mostly implemented in the compiler. The JVM has no
 * knowledge of a generic type.
 * 
 * All actual type parameters are erased during compile time using a process
 * known as erasure.
 * 
 * Compile-time type-safety is the benefit that you get when you use a
 * parameterized generic type in your code without the need to use casts.
 * 
 * @author mdama1
 *
 * @param <T>
 */
public class GenericAction<T> {

	private T action = null;

	public GenericAction(T action) {
		this.action = action;
	}

	public T getAction() {
		return action;
	}

	public void setAction(T action) {
		this.action = action;
	}
	
	public void execute(T t) {
		System.out.println("Some Generic Action");
	}
	
	public static void main(String[] args) {
		GenericAction<String> someStringAction = new GenericAction<String>("Say Hello");
		System.out.println(someStringAction.getAction()); // no cast required

		GenericAction<Integer> someIntegerAction = new GenericAction<Integer>(1);
		System.out.println(someIntegerAction.getAction()); // no compilation error

		// SuperType and SubType relationship - Super class and subtype relationships
		// are not applicable for generics

		// It is fine to store a String object in objectWrapper. After all, if you
		// intended to store an Object in objectWrapper, a String is also an Object.
		GenericAction<Object> someObjectAction = new GenericAction<Object>("Say Hi");
		System.out.println(someObjectAction.getAction());

		// someObjectAction = someStringAction;
		// String s = someObjectAction.getAction();
	}
}
