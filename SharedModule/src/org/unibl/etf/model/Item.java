package org.unibl.etf.model;

import java.io.Serializable;
import java.net.URL;

public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String title;
	private double price;
	private URL imageURL;
	
	public Item() {
		super();
	}

	public Item(String code, String title, double price, URL imageURL) {
		super();
		this.code = code;
		this.title = title;
		this.price = price;
		this.imageURL = imageURL;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public URL getImageURL() {
		return imageURL;
	}

	public void setImageURL(URL imageURL) {
		this.imageURL = imageURL;
	}
	
	@Override
	public String toString() {
		return code + " - " + title + " " + price + "€";
	}
	
}
