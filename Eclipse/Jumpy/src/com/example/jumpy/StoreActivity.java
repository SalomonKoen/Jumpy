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
		
		StoreAdapter adapter = new StoreAdapter(this, generateData());
		
		ListView listView = (ListView)findViewById(R.id.list);
		listView.setAdapter(adapter);
	}
	
	public ArrayList<StoreItem> generateData()
	{
		ArrayList<StoreItem> items = new ArrayList<StoreItem>();
		items.add(new StoreItem(1,"hello","hi",true,100));
		items.add(new StoreItem(2,"heasdfadllo","hi",false,100));
		items.add(new StoreItem(3,"helfffflo","hsdffci",true,1080));
		items.add(new StoreItem(4,"heldsdsdlo","h2sdfi",false,700));
		
		return items;
	}
}
