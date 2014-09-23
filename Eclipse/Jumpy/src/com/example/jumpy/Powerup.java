package com.example.jumpy;

public class Powerup extends Item
{
	private int type;
	private int value;
	
	public Powerup(int id, String name, String description, boolean multiple, int price, int image, int quantity, int type, int value)
	{
		super(id, name, description, multiple, price, image, quantity);
		this.value = value;
		this.type = type;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getValue()
	{
		return value;
	}
}
