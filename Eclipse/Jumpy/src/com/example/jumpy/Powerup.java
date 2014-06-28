package com.example.jumpy;

public class Powerup extends Item
{
	private PowerType type;
	private int value;
	
	public Powerup(int id, String name, String description, boolean multiple, int type, int value)
	{
		super(id, name, description, multiple);
		
		switch (type)
		{
			case 0:
				this.type = PowerType.a;
				break;
			case 1:
				this.type = PowerType.b;
				break;
			case 2:
				this.type = PowerType.c;
				break;
		}
		
		this.value = value;
	}
	
	
}
