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
		super(context, R.layout.score_layout,score);
		this.context = context;
		this.scores = score;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// get the inflater that will convert the person_layout.xml file into an
		// actual object (i.e. inflate it)
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// create a view to display the person's info
		View scoreView = inflater.inflate(R.layout.score_layout, parent, false);
		
		// keep track of person this view is working with
		scoreView.setTag(scores.get(position));
		
		// get text views that will hold strings
		TextView txtName = (TextView) scoreView.findViewById(R.id.tvName);
		TextView txtScore = (TextView) scoreView.findViewById(R.id.tvScore);
		
		// set text fields
		txtName.setText(scores.get(position).getName());
		txtScore.setText(String.valueOf(scores.get(position).getScore()));
		
		// return view to ListView to display
		return scoreView;
	}


}
