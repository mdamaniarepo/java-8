package com.java8musings.reflection;

import com.java8musings.innerclasses.Phone;
import com.java8musings.innerclasses.Phone.Sim;

/***
 * When you create an object in your program, Java loads the class’s byte code
 * and creates an object of the Class class to represent the byte code
 * 
 * 
 * @author mdama1oo
 *
 */
public class ClassReflection {

	@SuppressWarnings("unchecked")
	public <T> Class<T> getClassObjectUsingGetClass(T t) {
		return (Class<T>) t.getClass();
	}

	/**
	 * This method should be used when we do not know the class name till runtime
	 * 
	 * The forName(String className) method initializes the class if it is not
	 * already initialized, whereas the use of a class literal does not initialize
	 * the class
	 * 
	 * When a class is initialized, all its static initializers are executed and all
	 * static fields are initialized.
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> getClassObjectUsingForName(String className) throws ClassNotFoundException {
		// return Class.forName(className);
		return Class.forName(className, true, getClass().getClassLoader());
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ClassReflection testClassReflection = new ClassReflection();
		Class<Phone> phoneClass1 = Phone.class;
		System.out.println(phoneClass1.getTypeName());

		Class<Phone> phoneClass2 = testClassReflection.getClassObjectUsingGetClass(new Phone("Iphone", "6S"));
		System.out.println(phoneClass2.getTypeName());

		Class<Phone> phoneClass3 = null;
		try {
			phoneClass3 = (Class<Phone>) testClassReflection
					.getClassObjectUsingForName("com.java8musings.innerclasses.Phone");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(phoneClass3.getTypeName());
		
		// inner class
		Class<Sim> phoneClass4 = null;
		try {
			phoneClass4 = (Class<Sim>) testClassReflection.getClassObjectUsingForName("com.java8musings.innerclasses.Phone$Sim");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(phoneClass4.getTypeName());
	}
}
