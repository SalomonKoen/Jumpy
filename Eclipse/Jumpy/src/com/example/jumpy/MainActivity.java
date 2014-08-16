package com.example.jumpy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity
{
	private Intent musicService;
	
	@Override
	protected void onStart()
	{
		super.onStart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		JumpyApplication app = (JumpyApplication)this.getApplication();
		
		SQLiteHelper helper = new SQLiteHelper(this);
		
		app.setHelper(helper);
		
		if (!Settings.loadSettings(getSharedPreferences("Settings", 0), app))
		{
			ShowAlert();
		}
		
		JumpyApplication application = (JumpyApplication)getApplication();
		application.setVolume(Settings.getMusic());
		application.resume();
	}
	
	private void ShowAlert()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Create new Profile");
		alert.setMessage("Enter your name:");
		alert.setCancelable(false);

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				JumpyApplication app = (JumpyApplication)MainActivity.this.getApplication();
				
				SQLiteHelper helper = app.getHelper();
				
				String name = input.getText().toString();
				
				app.setPlayer(helper.addPlayer(name));
			 }
		});

		alert.show();
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
	
	public void onQuitClick(View view)
	{
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_delete)
        .setTitle("Quit")
        .setMessage("Are you sure you want to quit?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }

        })
        .setNegativeButton("No", null)
        .show();
	}
	
	public void onSettingsClick(View view)
	{
		Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
		startActivity(intent);
	}
	
	public void onStoreClick(View view)
	{
		Intent intent = new Intent(MainActivity.this, StoreActivity.class);
		startActivity(intent);
	}
	
	public void onHighScoreClick(View view)
	{
		Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
		startActivity(intent);
	}
	
	public void onProfileClick(View view)
	{
		Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		
		JumpyApplication app = (JumpyApplication)this.getApplication();
		
		app.getHelper().savePlayer(app.getPlayer());
		
		Settings.savePlayer(getSharedPreferences("Settings", 0), app.getPlayer().getId());
		app.closeConnection();
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
