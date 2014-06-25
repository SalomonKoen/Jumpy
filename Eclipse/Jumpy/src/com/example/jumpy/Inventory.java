package com.example.jumpy;

import java.util.ArrayList;

public class Inventory
{
	private ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

	public Inventory(ArrayList<InventoryItem> items)
	{
		this.items = items;
	}
	
	public void addItem(Item item, int quantity)
	{
		this.items.add(new InventoryItem(item, quantity));
	}
}
