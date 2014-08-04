package com.example.jumpy;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends ArrayAdapter<Item>
{
	private final Context context;
	private Player player;
	
	public StoreAdapter(Context context, Player player)
	{
		super(context, R.layout.store_item, player.getInventory().getItems());
		
		this.context = context;		
		this.player = player;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.store_item, parent, false);
		
		final Item cur = player.getInventory().getItems().get(position);
		
		ImageView image = (ImageView)rowView.findViewById(R.id.image);
		
		TextView name = (TextView)rowView.findViewById(R.id.name);
		TextView description = (TextView)rowView.findViewById(R.id.description);
		
		TextView quantity = (TextView)rowView.findViewById(R.id.quantity);
		final Button buy = (Button)rowView.findViewById(R.id.buy);
		
		buy.setText(Integer.toString(cur.getPrice()));
		
		buy.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				player.buyItem(cur);
				
				if (cur.getPrice() > player.getCoins())
				{
					buy.setEnabled(false);
				}
				
				if (!cur.isMultiple() && cur.getQuantity() == 1)
				{
					buy.setEnabled(false);
				}
				
				notifyDataSetChanged();
			}
		});
		
		name.setText(cur.getName());
		description.setText(cur.getDescription());
		
		if (cur.isMultiple())
			quantity.setText(Integer.toString(cur.getQuantity()));
		else
		{
			quantity.setText("");
			
			if (cur.getQuantity() == 1)
			{
				buy.setEnabled(false);
			}
		}
		
		if (cur.getPrice() > player.getCoins())
		{
			buy.setEnabled(false);
		}
		
		return rowView;
	}
}
