package com.java8musings.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import com.java8musings.innerclasses.Phone;

public class ExecutableReflection {

	static ClassReflection classReflection = new ClassReflection();


	// Methods
	public <T> Constructor<?>[] getConstructors(Class<T> clazz) {
		return clazz.getConstructors();
	}

	public <T> Constructor<?>[] getDeclaredConstructors(Class<T> clazz) {
		return clazz.getDeclaredConstructors();
	}

	public <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		return clazz.getConstructor(parameterTypes);
	}

	public <T> Constructor<T> getDeclaredConstructor(Class<T> clazz, Class<?>... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		return clazz.getDeclaredConstructor(parameterTypes);
	}
	
	
	// Methods
	
	public <T> Method[] getMethods(Class<T> clazz) {
		return clazz.getMethods();
	}

	public <T> Method[] getDeclaredMethods(Class<T> clazz) {
		return clazz.getDeclaredMethods();
	}

	public <T> Method getMethod(Class<T> clazz, String name, Class<?>... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		return clazz.getMethod(name, parameterTypes);
	}

	public <T> Method getDeclaredMethod(Class<T> clazz, String name, Class<?>... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		return clazz.getDeclaredMethod(name, parameterTypes);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutableReflection execReflection = new ExecutableReflection();
		try {
			Class<Phone> clazz = (Class<Phone>) classReflection
					.getClassObjectUsingForName("com.java8musings.innerclasses.Phone");
			Constructor<?>[] constructors = execReflection.getDeclaredConstructors(clazz);
			print(constructors);
			
			Method[] methods = execReflection.getDeclaredMethods(clazz);
			print(methods);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void print(Executable[] executables) {
		for (Executable executable : executables) {
			printExecutable(executable);
		}
	}

	private static void printExecutable(Executable executable) {
		StringBuffer buff = new StringBuffer();
		buff.append(Modifier.toString(executable.getModifiers()));
		
		if(executable instanceof Method) { 
			buff.append(" ");
			buff.append(((Method) executable).getReturnType().getName());
		}
		buff.append(" ");
		buff.append(executable.getName());
		buff.append("(");
		for(Parameter parameter : executable.getParameters()) {
			buff.append(parameter.getType());
			buff.append(" ");
			buff.append(parameter.getName());
			buff.append(" ");
			buff.append(" ");
		}
		buff.append(")");
		
		for (Class<?> execClazz : executable.getExceptionTypes()) {
			buff.append(" ");
			buff.append(execClazz.getClass());
			buff.append(" ");
			buff.append(execClazz.getName());
			buff.append(" ");
		}
		
		System.out.println(buff.toString());
	}
	
	

}
