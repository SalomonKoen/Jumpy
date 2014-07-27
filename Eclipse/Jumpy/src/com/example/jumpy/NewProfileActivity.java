package com.example.jumpy;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProfileActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_profile);
		
		final Button button = (Button)findViewById(R.id.create);
		button.setEnabled(false);
		
		EditText text = (EditText)findViewById(R.id.txtName);
		
		text.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				if (count > 0)
					button.setEnabled(true);
				else
					button.setEnabled(false);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{

			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				
			}
		});
	}
	
	public void onCreateClick(View view)
	{
		EditText text = (EditText)findViewById(R.id.txtName);
		
		JumpyApplication app = (JumpyApplication)this.getApplication();
		
		SQLiteHelper helper = app.getHelper();
		
		String name = text.getText().toString();
		
		app.setPlayer(helper.addPlayer(name));
		
		finish();
	}
}
