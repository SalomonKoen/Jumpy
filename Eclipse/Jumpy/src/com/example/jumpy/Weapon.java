package com.example.jumpy;

public class Weapon extends Item
{
	private int damage;
	
	public Weapon(int id, String description, boolean multiple, int damage)
	{
		super(id, description, multiple);
		this.damage = damage;
	}

	public int getDamage()
	{
		return damage;
	}
}
