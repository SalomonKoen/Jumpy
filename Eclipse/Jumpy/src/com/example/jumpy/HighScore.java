package com.example.jumpy;

public class HighScore
{
	private int player_id;
	private String name;
	private int kills;
	private int height;
	
	public HighScore(int player_id, String name, int kills, int height)
	{
		this.player_id = player_id;
		this.name = name;
		this.kills = kills;
		this.height = height;
	}

	public int getPlayer_id()
	{
		return player_id;
	}

	public int getKills()
	{
		return kills;
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
