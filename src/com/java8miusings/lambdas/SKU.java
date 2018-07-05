package com.java8miusings.lambdas;

public class SKU implements CatalogEntity {

	private Long id;

	private String name;

	public SKU() {
	}

	public SKU(Long id) {
		this.id = id;
	}

	public SKU(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return String.format("%s - %s ", id, name);
	}

}
