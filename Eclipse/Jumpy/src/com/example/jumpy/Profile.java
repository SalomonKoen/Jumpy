package com.example.jumpy;

public class Profile 
{
	private int player_id;
	private String name;
	private boolean selected = false;
	private boolean active = false;

	public Profile(String name, int player_id)
	{
		this.name = name;
		this.active = false;
		this.player_id = player_id;
	}
	
	public Profile(String name, int player_id, boolean active)
	{
		this.name = name;
		this.active = active;
		this.player_id = player_id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public int getPlayer_id()
	{
		return player_id;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
}
