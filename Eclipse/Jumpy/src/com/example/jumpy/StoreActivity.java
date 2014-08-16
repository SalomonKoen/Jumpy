package com.example.jumpy;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class StoreActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_menu);
		
		JumpyApplication application = (JumpyApplication)getApplication();
		
		Player player = application.getPlayer();
		
		StoreAdapter adapter = new StoreAdapter(this, player);
		
		ListView listView = (ListView)findViewById(R.id.list);
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void onPause()
	{
		JumpyApplication application = (JumpyApplication)this.getApplication();
		
		if (!this.isFinishing())
		{
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
