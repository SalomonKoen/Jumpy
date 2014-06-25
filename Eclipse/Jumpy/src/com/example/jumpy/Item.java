package com.example.jumpy;

public abstract class Item
{
	protected int id;
	protected String description;
	protected boolean multiple;

	public Item(int id, String description, boolean multiple)
	{
		this.description = description;
		this.multiple = multiple;
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
	
	
}
