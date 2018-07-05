package com.java8musings.reflection;

import java.lang.reflect.Field;

import com.java8musings.innerclasses.Phone;

public class FieldReflection {
	static ClassReflection classReflection = new ClassReflection();
		
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		FieldReflection fieldReflection = new FieldReflection();
		
		try {
			Class<Phone> clazz = (Class<Phone>) classReflection.getClassObjectUsingForName("com.java8musings.innerclasses.Phone");
			Field [] fields = fieldReflection.getDeclaredFields(clazz);
			print(fields);
			
			fields = fieldReflection.getFields(clazz);
			print(fields);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void print(Field[] fields) {
		for(Field field : fields) {
			System.out.println(field.getName());
		}
	}


	/***
	 * Only fields declared in class
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> Field[] getDeclaredFields(Class<T> clazz) {
		return clazz.getDeclaredFields();
	}
	
	/**
	 * All accessible (only public)fields of the class along with inherited fields 
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> Field[] getFields(Class<T> clazz) {
		return clazz.getFields();
	}
	
	public <T> Field getDeclaredField(Class<T> clazz, String fieldName) throws NoSuchFieldException, SecurityException {
		return clazz.getDeclaredField(fieldName);
	}
	
	public <T> Field getField(Class<T> clazz, String fieldName) throws NoSuchFieldException, SecurityException {
		return clazz.getField(fieldName);
	}

}
