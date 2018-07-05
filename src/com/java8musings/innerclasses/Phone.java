package com.java8musings.innerclasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstraing behaviour of inner class
 * 
 * Outer and Inner class Adapter, Enumeration and State pattern Callback
 * mechanisms can be easily developed Helps implement closures
 * 
 * @author mdama1
 *
 */
public class Phone { // Outer class
	
	/**
	 * while instantiating
	 */
	{
		System.out.println("Hi! I am main Phone class");
	}
	
	/**
	 * while initializing
	 */
	static {
		System.out.println("Hi! I am static block of Phone class");
	}

	private String modelNumber;

	private String brandName;

	public Phone(String brandName, String modelNumber) {
		this.modelNumber = modelNumber;
		this.brandName = brandName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public String getBrandName() {
		return brandName;
	}

	// Inner class Sim or also known as member inner class
	/**
	 * This class can be defined private, protected or public if public then
	 * instance can be created outside of the enclosing class. Refer test class for
	 * example
	 * No statics are allowed inside inner class i.e. no static field, method or initializers
	 * 
	 * @author mdama1
	 *
	 */
	public class Sim {

		private Map<String, Plan> plans = new HashMap<String, Plan>();

		private String number;

		public Sim(String number) {
			this.number = number;
		}

		public String getNumber() {
			return number;
		}

		public Map<String, Plan> getPlans() {
			return plans;
		}

		public Plan getDataPlan() {
			String planType = "DATA";
			if (plans.containsKey(planType)) {
				return plans.get(planType);
			}

			/**
			 * Scope of local inner class DataPlan is only within the method. This class
			 * cannot be instantiated outside the scope of method. As scope is limited to
			 * enclosing block no access modifiers can be used Local inner class can be
			 * created in static initializer block, non-static initializer block and
			 * constructor Local inner class can access all program elements of the
			 * enclosing class
			 * 
			 * Inorder to make this useful, implements an interface or extend it to a super
			 * class and override its method
			 * 
			 * @author mdama1
			 *
			 */
			class DataPlan implements Plan {
				{
					System.out.println("I am a local innner class for Data Plan");
				}

				@Override
				public boolean isEnabled() {
					return true;
				}

				@Override
				/**
				 * local variables can be used inside the local inner class but only if they are declared as final or effectively final
				 * 
				 */
				public String planType() {
					return planType;
				}
			}
			
			// planType = ""; this is not allowed to make it effectively final
			Plan myPlan = new DataPlan();
			plans.put(myPlan.planType(), myPlan);
			return myPlan;
		}

		public Plan getVoicePlan() {

			if (plans.containsKey("VOICE")) {
				return plans.get("VOICE");
			}

			/**
			 * Anonymous class
			 * 
			 * it cannot be instantiated it cannot have a constructor cannot be used
			 * anywhere else
			 * 
			 */
			Plan myVoicePlan = new Plan() {

				{
					System.out.println("Hi! I am an Anonymous inner class");
				}

				@Override
				public String planType() {
					return "VOICE";
				}

				@Override
				public boolean isEnabled() {
					return true;
				}
			};

			plans.put(myVoicePlan.planType(), myVoicePlan);
			return myVoicePlan;
		}

		// Inner class extending the elements of outer class/enclosing class
		public void printMyDetails() {
			System.out.println(getBrandName() + ", " + getModelNumber() + ", " + getNumber());
		}

	}

	/**
	 * This classes are called nested classes and not member inner class To create
	 * 
	 * instance of Member inner class, local classes and anonymous classes you need
	 * instances of enclosing class. This is sometimes not needed when inner classes
	 * are defined inside static method or static initializers
	 * 
	 * 
	 * @author mdama1
	 *
	 */
	public static class Service {
		private boolean freeService;

		private String duration;

		public Service(boolean freeService, String duration) {
			this.freeService = freeService;
			this.duration = duration;
		}

		public boolean isFreeService() {
			return freeService;
		}

		public String getDuration() {
			return duration;
		}

	}

}
