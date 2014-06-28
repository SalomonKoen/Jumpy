package com.example.jumpy;

public class StoreItem extends Item
{
	private int price;
	
	public StoreItem(int id, String name, String description, boolean multiple, int price)
	{
		super(id, name, description, multiple);
		
		this.price = price;
	}
}
