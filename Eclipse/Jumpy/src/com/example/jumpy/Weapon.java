package com.example.jumpy;

public class Weapon extends Item
{
	private int damage;
	
	public Weapon(int id, String name, String description, boolean multiple, int price, int quantity, int damage)
	{
		super(id, name, description, multiple, price, quantity);
		this.damage = damage;
	}

	public int getDamage()
	{
		return damage;
	}
}
