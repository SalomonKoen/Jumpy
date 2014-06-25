package com.example.jumpy;

public class Character extends Item
{
	private int mass;
	private int health;
	
	public Character(int id, String description, boolean multiple, int mass, int health)
	{
		super(id, description, multiple);
		this.mass = mass;
		this.health = health;
	}
}
