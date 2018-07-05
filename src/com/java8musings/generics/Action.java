package com.java8musings.generics;

/**
 * Typical implementation for wrapping Action without generics
 * 
 * problem with the implementation
 * 
 * 1. Although I know that i am passing String I still have to explicitly cast
 * it back to String while accessing the get
 * 
 * 2. There is no way to know what is the type of action class, even if i pass
 * Integer it will not give me a compilation error in main method.
 * 
 * Wouldn't it be nice that class had a way of letting you tell that you want to
 * use it for a specific type
 * 
 * @author mdama1
 *
 */
public class Action {

	private Object action;

	public Action(Object action) {
		this.action = action;
	}

	public Object getAction() {
		return action;
	}

	public void setAction(Object action) {
		this.action = action;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Action actionHandler = new Action("Login Action");
		String action = (String) actionHandler.getAction();
		System.out.println(action);

		Action actionHandler1 = new Action(1);
		String action1 = (String) actionHandler1.getAction();
		System.out.println(action1);
	}

}
