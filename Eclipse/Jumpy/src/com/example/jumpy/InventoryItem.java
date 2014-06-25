package com.example.jumpy;

public class InventoryItem
{
	private int quantity;
	private Item item;
	
	public InventoryItem(Item item, int quantity)
	{
		this.quantity = quantity;
		this.item = item;
	}
}
