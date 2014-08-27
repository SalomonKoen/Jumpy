package com.example.jumpy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingsActivity extends Activity
{
	private RadioGroup rgGraphics = null;
	private SeekBar seekMusic = null;
	private SeekBar seekEffects = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_menu);
		
		rgGraphics = (RadioGroup)findViewById(R.id.rgGraphics);		
		seekMusic =  (SeekBar)findViewById(R.id.seekMusic);
		seekEffects =  (SeekBar)findViewById(R.id.seekEffects);
		
		seekMusic.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				
			}
		});
		
		seekEffects.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				
			}
		});
		
		if (!Settings.isLoaded())
		{
			Settings.loadSettings(getSharedPreferences("Settings", 0), (JumpyApplication)this.getApplication());
		}
		
		int quality = Settings.getGraphics();
		
		if (quality == 0)
			rgGraphics.check(R.id.rbtnLow);
		else if (quality == 1)
			rgGraphics.check(R.id.rbtnMedium);
		else
			rgGraphics.check(R.id.rbtnHigh);
		
		seekMusic.setProgress(Settings.getMusic());
		seekEffects.setProgress(Settings.getEffects());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onSaveClick(View view)
	{
		int quality = 1;
		
		if (rgGraphics.getCheckedRadioButtonId() == R.id.rbtnLow)
			quality = 0;
		else if (rgGraphics.getCheckedRadioButtonId() == R.id.rbtnMedium)
			quality = 1;
		else
			quality = 2;
		
		Settings.saveSettings(getSharedPreferences("Settings", 0), seekMusic.getProgress(), seekEffects.getProgress(), quality);
		
		finish();
	}
	
	public void onCancelClick(View view)
	{
		finish();
	}
	
	@Override
	protected void onPause()
	{
		if (this.isFinishing())
		{
			JumpyApplication application = (JumpyApplication)this.getApplication();
			application.pause();
		}
		
		super.onPause();
	}
	
	@Override
	protected void onResume()
	{
		JumpyApplication application = (JumpyApplication)this.getApplication();
		application.resume();
		
		super.onResume();
	}
}
