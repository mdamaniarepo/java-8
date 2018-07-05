package com.java8musings.generics;

public class LookupHandler {

	/**
	 * Handle lookup method is supposed to do lookup of any type hence Object seems
	 * more suitable.
	 * 
	 * @param lookup
	 */
	public void handleLookup(Lookup<Object> lookup) {
		lookup.executeLookup();
	}

	
	public void handleGenericLookup(Lookup<?> lookup) {
		lookup.executeLookup();
	}

}
