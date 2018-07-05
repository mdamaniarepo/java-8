package com.java8musings.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.java8musings.innerclasses.Phone;

/***
 * Reflection for creating object of a class using no-arg constructor using
 * 
 * 
 * @author mdama1
 *
 */
public class ReflectionUsecases {

	private String className = "I am a private field";

	public String classNamePublic = "I am a public field";

	public static void main(String[] args) {
		try {
			// creating object of no arg constructor
			ReflectionUsecases reflectionUsecases = ReflectionUsecases.class.newInstance();
			System.out.println(reflectionUsecases);

			// creating object of n argument constructor
			Phone phone = Phone.class.getConstructor(String.class, String.class).newInstance("Samsung", "Galaxy");
			System.out.println(phone);

			// invoking method
			ExecutableReflection executableReflection = new ExecutableReflection();
			System.out.println(
					executableReflection.getMethod(phone.getClass(), "getBrandName", null).invoke(phone, null));

			// invoking static method
			reflectionUsecases.getClass().getMethod("someStaticMethod", null).invoke(null, null);

			// accessing field
			FieldReflection fieldReflection = new FieldReflection();
			System.out.println("Public field: " + fieldReflection
					.getField(reflectionUsecases.getClass(), "classNamePublic").get(reflectionUsecases));

			// accessing private field
			Field privateField = fieldReflection.getDeclaredField(reflectionUsecases.getClass(), "className");
			privateField.setAccessible(Boolean.TRUE);
			System.out.println("Private field: " + privateField.get(reflectionUsecases));

			// updating field
			privateField.set(reflectionUsecases, "Crime! Private got updated");
			System.out.println("Private field: " + privateField.get(reflectionUsecases));

			SecurityManager securityManager = System.getSecurityManager();
			if (securityManager == null) {
				System.out.println("Security manager is not installed");
			}

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | NoSuchFieldException e) {
			/**
			 * InstantiationException is thrown if there was any problem in creating the
			 * object, for example, attempting to create an object of an abstract class, an
			 * interface type, primitive types, or the void type. This exception may also be
			 * thrown if the class does not have a no-args constructor.
			 */

			/**
			 * The IllegalAccessException may be thrown if the class itself is not
			 * accessible or the no-args constructor is not accessible. For example, if
			 * there is a no-args constructor and it is declared private. In this case, this
			 * exception will be thrown.
			 */
			e.printStackTrace();
		}
	}

	public static void someStaticMethod() {
		System.out.println("I am a static method");
	}

}
