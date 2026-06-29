package org.unibl.etf.dto;

import org.unibl.etf.model.Item;
import org.unibl.etf.model.Supplier;

public class OrderRequestDTO {
	private int id;
	private Item item;
	private Supplier supplier;
	
	public OrderRequestDTO() {
		super();
	}

	public OrderRequestDTO(int id, Item item, Supplier supplier) {
		super();
		this.id = id;
		this.item = item;
		this.supplier = supplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
