using UnityEngine;
using System.Collections;

public class Powerup
{
	private int quantity;
	private int type;
	private int value;
	
	public Powerup(int quantity, int type, int value)
	{
		this.quantity = quantity;
		this.value = value;
		this.type = type;
	}

	public int getQuantity()
	{
		return quantity;
	}
}
