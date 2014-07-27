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
		
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Character(1,"Blabla","adslkd dkdjdd fhfhdkdf dk", false, 50, 1, 20, 100));
		items.add(new Weapon(1,"aDSdf","asdff fdfs sd  ds ffffffffffffffd ddddddddddkdjdd fhfhdkdf dk", true, 50, 1, 2));
		items.add(new Powerup(1,"e rwwer","adslkd dkdjdd fhfhdkdf dk", true, 50, 1, 3, 200));
		items.add(new Character(1,"E ds s","adslkd dkdjdd fhfhdkdf dk", false, 50, 1, 20, 100));
		items.add(new Character(1,"Blabla","adslkd dkdjdd fhfhdkdf dk", false, 50, 1, 20, 100));
		items.add(new Weapon(1,"aDSdf","asdff fdfs sd  ds ffffffffffffffd ddddddddddkdjdd fhfhdkdf dk", true, 50, 1, 2));
		items.add(new Powerup(1,"e rwwer","adslkd dkdjdd fhfhdkdf dk", true, 50, 1, 3, 200));
		items.add(new Character(1,"E ds s","adslkd dkdjdd fhfhdkdf dk", false, 50, 1, 20, 100));
		
		Inventory inventory = new Inventory(items);
		Player player = ((JumpyApplication)this.getApplication()).getPlayer();
		
		player.setInventory(inventory);
		
		StoreAdapter adapter = new StoreAdapter(this, player);
		
		ListView listView = (ListView)findViewById(R.id.list);
		listView.setAdapter(adapter);
	}
}
