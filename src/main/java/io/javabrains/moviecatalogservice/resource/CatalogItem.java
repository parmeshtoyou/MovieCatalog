package io.javabrains.moviecatalogservice.resource;

public class CatalogItem {
	
	private String name;
	private String desc;
	private int rating;
	
	public CatalogItem(String name, String desc, int rating) {
		this.name = name;
		this.desc = desc;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public int getRating() {
		return rating;
	}
}
