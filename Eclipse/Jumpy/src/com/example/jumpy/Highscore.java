package com.example.jumpy;

public class Highscore
{
	private int player_id;
	private int score;
	private int height;
	
	public Highscore(int player_id, int score, int height)
	{
		this.player_id = player_id;
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
	
}
