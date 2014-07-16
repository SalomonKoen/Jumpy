package com.example.jumpy;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends ArrayAdapter<Item>
{
	private final Context context;
	private ArrayList<Item> items = new ArrayList<Item>();
	private Player player;
	
	public StoreAdapter(Context context, ArrayList<Item> items, Player player)
	{
		super(context, R.layout.store_item, items);
		
		this.context = context;
		
		if (items != null)
			this.items = items;
		
		this.player = player;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.store_item, parent, false);
		
		Item cur = items.get(position);
		
		ImageView image = (ImageView)rowView.findViewById(R.id.image);
		
		TextView name = (TextView)rowView.findViewById(R.id.name);
		TextView description = (TextView)rowView.findViewById(R.id.description);
		
		TextView quantity = (TextView)rowView.findViewById(R.id.quantity);
		Button buy = (Button)rowView.findViewById(R.id.buy);
		
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
		
		buy.setText(Integer.toString(cur.getPrice()));
		
		return rowView;
	}
}
