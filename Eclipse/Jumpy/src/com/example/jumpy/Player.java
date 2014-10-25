package com.example.jumpy;

import java.util.ArrayList;

public class Player
{
	private int id;
	private String name;
	private int coins;
	
	private Inventory inventory;
	
	public Player(int id, String name, int coins, Inventory inventory)
	{
		this.id = id;
		this.name = name;
		this.coins = coins;
		this.inventory = inventory;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getCoins()
	{
		return coins;
	}

	public Inventory getInventory()
	{
		return inventory;
	}
	
	public void setInventory(Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	public void buyItem(Item item)
	{
		item.add();
		coins -= item.getPrice();
	}

	public ArrayList<Powerup> getPowerups()
	{
		ArrayList<Powerup> powerups = new ArrayList<Powerup>();
		
		for (Item item : inventory)
		{
			if (item instanceof Powerup)
			{
				powerups.add((Powerup)item);
			}
		}
		
		return powerups; 
	}

	public void setPowerups(int[] powerups)
	{
		if (powerups.length != 0)
		{
			int i = 0;
			
			for (Item item : inventory)
			{
				if (item instanceof Powerup)
				{
					Powerup cur = (Powerup)item;
					cur.setQuantity(powerups[i++]);
				}
			}
		}
	}
}
