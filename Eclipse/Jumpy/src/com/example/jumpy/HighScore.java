package com.example.jumpy;

public class HighScore
{
	private int player_id;
	private String name;
	private int score;
	private int height;
	
	public HighScore(int player_id, String name, int score, int height)
	{
		this.player_id = player_id;
		this.name = name;
		this.score = score;
		this.height = height;
	}

	public int getPlayer_id()
	{
		return player_id;
	}

	public int getScore()
	{
		return score;
	}

	public int getHeight()
	{
		return height;
	}
	
	public String getName()
	{
		return name;
	}
	
}
