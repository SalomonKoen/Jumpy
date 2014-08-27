package com.example.jumpy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class HighScoreActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_high_score);

		ListView highscores = (ListView)findViewById(R.id.lvHighScores);
		
		final List<HighScore> items = new ArrayList<HighScore>();
		items.add(new HighScore(1, "John", 14, 20));
		items.add(new HighScore(2, "Jane", 16, 30));
		items.add(new HighScore(3, "Solly", 18, 10));

		// create adapter to transform string items
		final HighScoreAdapter adapter = new HighScoreAdapter(this, items);
		
		// attach adapter to list view
		highscores.setAdapter(adapter);

	}
}
