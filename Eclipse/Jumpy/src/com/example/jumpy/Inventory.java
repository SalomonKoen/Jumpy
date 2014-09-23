package com.example.jumpy;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory implements Iterable<Item>
{
	private ArrayList<Item> items = new ArrayList<Item>();

	public Inventory(ArrayList<Item> items)
	{
		this.items = items;
	}
	
	public void addItem(Item item)
	{
		this.items.add(item);
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}

	@Override
	public Iterator<Item> iterator()
	{
		return new Iterator<Item>()
		{
			private int pos = 0;
			
			@Override
			public void remove()
			{
				
			}
			
			@Override
			public Item next()
			{
				return items.get(pos);
			}
			
			@Override
			public boolean hasNext()
			{
				return pos < items.size();
			}
		};
	}
}
