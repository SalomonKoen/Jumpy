package com.example.jumpy;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProfileAdapter extends ArrayAdapter<Profile> {
	private Context context;
	private List<Profile> profiles;

	public ProfileAdapter(Context context, List<Profile> profiles)
	{
		super(context, R.layout.item_high_score,profiles);
		this.context = context;
		this.profiles = profiles;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// get the inflater that will convert the person_layout.xml file into an
		// actual object (i.e. inflate it)
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// create a view to display the person's info
		View profileView = inflater.inflate(R.layout.activity_profiles, parent, false);
		
		// keep track of person this view is working with
		profileView.setTag(profiles.get(position));
		
		// get text views that will hold strings
		TextView txtName = (TextView) profileView.findViewById(R.id.tvProfileName);
		
		// set text fields
		txtName.setText(profiles.get(position).getName());
		
		if (profiles.get(position).isActive())
			txtName.setTextColor(Color.RED);
		else if (profiles.get(position).isSelected())
			txtName.setTextColor(Color.GREEN);
		
		// return view to ListView to display
		return profileView;
	}
	
	public void changeProfile(Profile profile)
	{
		for (Profile p : profiles)
		{
			if (p.equals(profile))
				p.setActive(true);
			else
				p.setActive(false);
		}
		
		notifyDataSetChanged();
	}

	public void setActive(Profile selected)
	{
		for (Profile profile : profiles)
		{
			if (profile.isActive())
			{
				profile.setActive(false);
			}
			
			if (profile == selected)
			{
				profile.setActive(true);
			}
		}
		
		notifyDataSetChanged();
	}
}
