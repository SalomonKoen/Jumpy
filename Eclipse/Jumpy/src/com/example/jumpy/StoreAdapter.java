package com.example.jumpy;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends ArrayAdapter<StoreItem>
{
	private final Context context;
	private final ArrayList<StoreItem> items;
	
	public StoreAdapter(Context context, ArrayList<StoreItem> items)
	{
		super(context, R.layout.store_item, items);
		
		this.context = context;
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.store_item, parent, false);
		
		ImageView image = (ImageView)rowView.findViewById(R.id.image);
		TextView text = (TextView)rowView.findViewById(R.id.text);
		
		text.setText(items.get(position).getName());
		
		return rowView;
	}
}
