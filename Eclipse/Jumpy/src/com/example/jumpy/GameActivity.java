package com.example.jumpy;

import java.util.ArrayList;

import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity
{
	private JumpyApplication application = (JumpyApplication)getApplication();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    
    public void setHighScore(int height, int kills)
    {
    	application.getHelper().addHighScore(new HighScore(application.getPlayer().getId(), application.getPlayer().getName(), kills, height));
    }
    
    public ArrayList<Powerup> getPowerups()
    {
    	return application.getPlayer().getPowerups();
    }
}

