package com.example.jumpy;

public class Powerup extends Item
{
	private powerType type;
	private int value;
	
	public Powerup(int id, String description, boolean multiple, int type, int value)
	{
		super(id, description, multiple);
		
		switch (type)
		{
			case 0:
				this.type = powerType.a;
				break;
			case 1:
				this.type = powerType.b;
				break;
			case 2:
				this.type = powerType.c;
				break;
		}
		
		this.value = value;
	}
	
	
}
