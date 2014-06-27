package com.example.jumpy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity
{
	@Override
	protected void onStart()
	{
		super.onStart();
		
		Settings.loadSettings(getSharedPreferences("Settings", 0));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
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
}
