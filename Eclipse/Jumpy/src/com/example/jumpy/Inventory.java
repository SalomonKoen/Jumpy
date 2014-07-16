package com.example.jumpy;

import java.util.ArrayList;

public class Inventory
{
	private ArrayList<Item> items = new ArrayList<Item>();

	public Inventory(ArrayList<Item> items)
	{
		this.items = items;
	}
	
	public void addItem(Item item)
	{
		this.items.add(item);
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
}
