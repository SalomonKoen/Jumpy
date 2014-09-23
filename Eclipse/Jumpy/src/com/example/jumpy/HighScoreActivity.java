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
		
		JumpyApplication application = (JumpyApplication)getApplication();
		
		List<HighScore> items = application.getHelper().getHighScores(application.getPlayer().getId());

		HighScoreAdapter adapter = new HighScoreAdapter(this, items);
		highscores.setAdapter(adapter);

	}
}
