using UnityEngine;
using System.Collections;

public class HighScore 
{
	private int height;
	private string name;

	public HighScore(string name, int height)
	{
		this.name = name;
		this.height = height;
	}

	public int getHeight()
	{
		return height;
	}

	public string getName()
	{
		return name;
	}
}
