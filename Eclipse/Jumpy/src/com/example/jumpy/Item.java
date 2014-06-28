package com.example.jumpy;

public abstract class Item
{
	protected int id;
	protected String name;
	protected String description;
	protected boolean multiple;

	public Item(int id, String name, String description, boolean multiple)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.multiple = multiple;
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
	
	
}
