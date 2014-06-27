package com.example.jumpy;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Settings
{
	private static int music = 100;
	private static int effects = 100;
	private static int graphics = 1;
	
	private static boolean loaded = false;

	public static void loadSettings(SharedPreferences preferences)
	{
		music = preferences.getInt("musicVolume", music);
		effects = preferences.getInt("effectsVolume", effects);
		graphics = preferences.getInt("graphicsQuality", graphics);

		loaded = true;
	}

	public static int getGraphics()
	{
		return graphics;
	}

	public static int getMusic()
	{
		return music;
	}

	public static int getEffects()
	{
		return effects;
	}

	public static boolean isLoaded()
	{
		return loaded;
	}

	public static void saveSettings(SharedPreferences preferences, int music, int effects, int graphics)
	{
		Editor editor = preferences.edit();
		
		Settings.music = music;
		Settings.effects = effects;
		Settings.graphics = graphics;
		
		editor.putInt("musicVolume", music);
		editor.putInt("effectsVolume", effects);
		editor.putInt("graphicsQuality", graphics);
		
		editor.commit();
	}
	
}
