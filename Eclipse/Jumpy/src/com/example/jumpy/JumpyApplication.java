package com.example.jumpy;

import android.app.Application;

public class JumpyApplication extends Application
{
	private Player player;
	private SQLiteHelper helper;
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public SQLiteHelper getHelper()
	{
		return helper;
	}
	
	public void setHelper(SQLiteHelper helper)
	{
		this.helper = helper;
	}
	
	public void closeConnection()
	{
		helper.close();
	}
}
