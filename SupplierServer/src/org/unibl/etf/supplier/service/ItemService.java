package org.unibl.etf.supplier.service;

import java.util.List;

import org.unibl.etf.model.*;
import org.unibl.etf.supplier.repository.*;

public class ItemService {
	private final ItemRepository itemRepository;
	
	public ItemService() {
		itemRepository = ItemRepository.getInstance();
	}
	
	public List<Item> getAllItems(){
		return itemRepository.getItems();
	}
	
	public Item getItemByCode(String code) {
		for(Item item : itemRepository.getItems()) {
			if(item.getCode().equals(code))
				return item;
		}
		return null;
	}
	
	public List<Item> updateOffer(){
		return itemRepository.getUpdatedOffer();
	}
	
	public Item addItem(Item item) {
		if(itemRepository.getItems().add(item)) {
			return item;
		}
		return null;
	}
}