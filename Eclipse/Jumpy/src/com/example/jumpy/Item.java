package com.example.jumpy;

public abstract class Item
{
	protected int id;
	protected String name;
	protected String description;
	protected boolean multiple;
	protected int price;
	protected int quantity;

	public Item(int id, String name, String description, boolean multiple, int price, int quantity)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.multiple = multiple;
		this.price = price;
		
		if (!multiple && quantity > 1)
			this.quantity = 1;
		else
			this.quantity = quantity;
	}
	
	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public boolean isMultiple()
	{
		return multiple;
	}

	public int getId()
	{
		return id;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void add()
	{
		quantity++;
	}
}
