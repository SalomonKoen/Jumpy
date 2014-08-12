package com.example.jumpy;

public class Character extends Item
{
	private int mass;
	private int health;
	
	public Character(int id, String name, String description, boolean multiple, int price, int image, int quantity, int mass, int health)
	{
		super(id, name, description, multiple, price, image, quantity);
		this.mass = mass;
		this.health = health;
	}
}
