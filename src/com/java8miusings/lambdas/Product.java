package com.java8miusings.lambdas;

import java.util.ArrayList;
import java.util.List;

public class Product implements CatalogEntity {

	public static List<Product> products = new ArrayList<>();

	static {
		products.add(new Product(1L, "Mobile Product 1", 4L, "Mobile", "Samsung"));
		products.add(new Product(2L, "Mobile Product 2", 3L, "Mobile", "Iphone"));
		products.add(new Product(3L, "TV Product 3", 2L, "TV", "Sony"));
		products.add(new Product(4L, "WM Product 4", 1L, "WashingMachine", "LG"));
		products.add(new Product(5L, "TV Product 5", 5L, "TV", "Samsung"));
		products.add(new Product(6L, "Mobile Product 6", 3L, "Mobile", "Asus"));
	}

	public Long id;

	public String name;

	public Long rank;

	public String category;

	public String brandName;

	public Product() {
	}
	
	public Product(Long id) {
		this.id = id;
	}
	
	public Product(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Product(Long id, String name, Long rank, String category, String brandName) {
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.category = category;
		this.brandName = brandName;
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

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String toString() {
		return String.format("%s - %s - %s - %s - %s", id, name, rank, category, brandName);
	}

	public static List<Product> findAll() {
		return new ArrayList<Product>(products);
	}

}
