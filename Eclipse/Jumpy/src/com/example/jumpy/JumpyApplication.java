package com.example.jumpy;

import android.app.Application;
import android.media.MediaPlayer;

public class JumpyApplication extends Application
{
	private Player player;
	private SQLiteHelper helper;
	
	private MediaPlayer music;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		music = MediaPlayer.create(this, R.raw.background_music);
		music.setLooping(true);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Profile profile)
	{
		helper.saveItems(player);
		this.player = helper.getPlayer(profile.getPlayer_id());
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

	public void pause()
	{
		music.stop();
	}
	
	public void resume()
	{
		music.start();
	}

	public void setVolume(int volume)
	{
		music.setVolume(volume, volume);
	}
}
