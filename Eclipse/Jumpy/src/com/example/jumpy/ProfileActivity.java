package com.example.jumpy;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ProfileActivity extends Activity 
{
	Profile selected;
	ProfileAdapter adapter;
	
	private void ShowAlert()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Create new Profile");
		alert.setMessage("Enter your name:");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				JumpyApplication app = (JumpyApplication)ProfileActivity.this.getApplication();
				
				SQLiteHelper helper = app.getHelper();
				
				String name = input.getText().toString();
				
				Player player = helper.addPlayer(name);
				
				app.setPlayer(player);
				
				Profile profile = new Profile(name, player.getId());
				adapter.add(profile);
				
				adapter.changeProfile(profile);
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
		  public void onClick(DialogInterface dialog, int whichButton)
		  {

		  }
		});

		alert.show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_profile);

		ListView listView = (ListView)findViewById(R.id.lvProfiles);
		
		JumpyApplication app = (JumpyApplication)getApplication();
		
		SQLiteHelper helper = app.getHelper();
		
		final ArrayList<Profile> profiles = helper.getProfiles(app.getPlayer().getId());

		// create adapter to transform string items
		adapter = new ProfileAdapter(this, profiles);
		
		// attach adapter to list view
		listView.setAdapter(adapter);
		
		Button newProfile = (Button)findViewById(R.id.btnNew);
		
		newProfile.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ShowAlert();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (selected != null)
				{
					selected.setSelected(false);
				}
				
				selected = profiles.get(position);
				selected.setSelected(true);
				adapter.notifyDataSetChanged();
			}
		});

		Button changeBtn = (Button)findViewById(R.id.btnChange);
		changeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JumpyApplication application = (JumpyApplication)getApplication();
				application.getHelper().savePlayer(application.getPlayer());
				application.setPlayer(selected);
				adapter.setActive(selected);
			}
		});

	}
}
