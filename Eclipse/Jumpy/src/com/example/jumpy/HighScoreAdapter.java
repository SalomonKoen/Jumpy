package com.example.jumpy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighScoreAdapter extends ArrayAdapter<HighScore> 
{
	private Context context;
	private List<HighScore> scores;

	public HighScoreAdapter(Context context, List<HighScore> score)
	{
		super(context, R.layout.item_high_score,score);
		this.context = context;
		this.scores = score;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// get the inflater that will convert the person_layout.xml file into an
		// actual object (i.e. inflate it)
		
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_high_score, parent, false);
		}
		
		// keep track of person this view is working with
		convertView.setTag(scores.get(position));
		
		// get text views that will hold strings
		TextView txtName = (TextView) convertView.findViewById(R.id.tvName);
		TextView txtHeight = (TextView) convertView.findViewById(R.id.tvHeight);
		
		// set text fields
		txtName.setText(scores.get(position).getName());
		txtHeight.setText(String.valueOf(scores.get(position).getHeight()));
		
		// return view to ListView to display
		return convertView;
	}


}
