package com.java8musings.innerclasses;

import com.java8musings.innerclasses.Phone;
import com.java8musings.innerclasses.Phone.Sim;

public class Test {

	public static void main(String[] args) {
		Phone myPhone = new Phone("Iphone", "7");
		Sim mySim = myPhone.new Sim("1234567890");
		System.out.println("Data plan enabled: " + mySim.getDataPlan().isEnabled());
		System.out.println("Voice plan enabled: " + mySim.getVoicePlan().isEnabled());
		System.out.println("Plans: "+ mySim.getPlans());
		
		
		Phone.Service service = new Phone.Service(true, "12 months");
		System.out.println("Service duration: " + service.getDuration());
	}

}
