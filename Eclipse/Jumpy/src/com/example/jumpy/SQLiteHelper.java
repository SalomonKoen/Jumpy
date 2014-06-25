package com.example.jumpy;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UserData";
	
	public SQLiteHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		createPlayerTable(db);
		createHighscoreTable(db);
		createItemTable(db);
		createPowerupTable(db);
		createWeaponTable(db);
		createCharacterTable(db);
		createStoreTable(db);
		createInventoryItemTable(db);
		createEnemyTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS Highscore;");
		db.execSQL("DROP TABLE IF EXISTS Player;");
		db.execSQL("DROP TABLE IF EXISTS Powerup;");
		db.execSQL("DROP TABLE IF EXISTS Weapon;");
		db.execSQL("DROP TABLE IF EXISTS Character;");
		db.execSQL("DROP TABLE IF EXISTS Store;");
		db.execSQL("DROP TABLE IF EXISTS Item;");
		db.execSQL("DROP TABLE IF EXISTS InventoryItem;");
		db.execSQL("DROP TABLE IF EXISTS Enemy;");
		
		this.onCreate(db);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//CREATE TABLES
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private void createHighscoreTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Highscore ("
				+ "highscore_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "player_id INTEGER FOREIGN KEY REFERENCES Player(player_id), "
				+ "score INTEGER, "
				+ "height INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createPlayerTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Player ("
				+ "player_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT, "
				+ "coins INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createItemTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Item ("
				+ "item_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "description text, "
				+ "type INTEGER CONSTRAINT check_type CHECK (type IN (0, 1, 2)), "
				+ "multiple BOOLEAN);";
		
		db.execSQL(sql);
	}
	
	private void createCharacterTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Character ("
				+ "item_id INTEGER PRIMARY KEY REFERENCES Item(item_id), "
				+ "mass INTEGER, "
				+ "health INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createPowerupTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Powerup ("
				+ "item_id INTEGER PRIMARY KEY REFERENCES Item(item_id), "
				+ "type INTEGER, "
				+ "value INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createWeaponTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Weapon ("
				+ "item_id INTEGER PRIMARY KEY REFERENCES Item(item_id), "
				+ "damage INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createStoreTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Store ("
				+ "item_id INTEGER PRIMARY KEY REFERENCES Item(item_id), "
				+ "price INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createInventoryItemTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE InventoryItem ("
				+ "player_id INTEGER PRIMARY KEY REFERENCES Player(player_id), "
				+ "item_id INTEGER PRIMARY KEY REFERENCES Item(item_id), "
				+ "quantity INTEGER);";
		
		db.execSQL(sql);
	}
	
	private void createEnemyTable(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE Enemy ("
				+ "enemy_id INTEGER PRIMARY KEY, "
				+ "health INTEGER);";
		
		db.execSQL(sql);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//HIGHSCORE
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void addHighscore(Highscore highscore)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("player_id", highscore.getPlayer_id());
		values.put("score", highscore.getScore());
		values.put("height", highscore.getHeight());
		
		db.insert("Highscore", null, values);
		db.close();
	}
	
	public ArrayList<Highscore> getHeightscores(int player_id)
	{
		ArrayList<Highscore> highscores = new ArrayList<Highscore>();
		
		String sql = "SELECT * FROM Highscore "
				+ "WHERE player_id=" + player_id + " "
						+ "ORDER BY score DESC, height DESC;";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst())
		{
			do {
				Highscore highscore = new Highscore(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
				highscores.add(highscore);
			} while (cursor.moveToNext());
		}
		
		return highscores;
	}
	
	public ArrayList<Highscore> getHeightscores()
	{
		ArrayList<Highscore> highscores = new ArrayList<Highscore>();
		
		String sql = "SELECT * FROM Highscore "
				+ "ORDER BY score DESC, height DESC;";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst())
		{
			do
			{
				Highscore highscore = new Highscore(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
				highscores.add(highscore);
			} while (cursor.moveToNext());
		}
		
		return highscores;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//ITEM
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<Item> getItems()
	{
		ArrayList<Item> items = new ArrayList<Item>();
		
		String sql = "SELECT * FROM Item;";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		
		SQLiteDatabase db2 = this.getReadableDatabase();
		
		if (cursor.moveToFirst())
		{
			do
			{
				Item item = null;
				
				int id = cursor.getInt(0);
				int type = cursor.getInt(2);
				String description = cursor.getString(1);
				boolean multiple = cursor.getInt(3) > 0;
				
				if (type == 0)
				{
					String sql2 = "SELECT * FROM Character "
							+ "WHERE item_id=" + id + ";";
					
					Cursor cursor2 = db2.rawQuery(sql2, null);
					
					if (cursor2.moveToFirst())
					{
						item = new Character(id, description, multiple, cursor2.getInt(1), cursor2.getInt(2));
					}
				}
				else if (type == 1)
				{
					String sql2 = "SELECT * FROM Powerup "
							+ "WHERE item_id=" + id + ";";
					
					Cursor cursor2 = db2.rawQuery(sql2, null);
					
					if (cursor2.moveToFirst())
					{
						item = new Powerup(id, description, multiple, cursor2.getInt(1), cursor2.getInt(2));
					}
				}
				else if (type == 2)
				{
					String sql2 = "SELECT * FROM Weapon "
							+ "WHERE item_id=" + id + ";";
					
					Cursor cursor2 = db2.rawQuery(sql2, null);
					
					if (cursor2.moveToFirst())
					{
						item = new Weapon(id, description, multiple, cursor2.getInt(1));
					}
				}
				
				items.add(item);
				
			} while (cursor.moveToNext());
		}
		
		return items;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//PLAYER
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Player addPlayer(String name)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("name", name);
		
		db.insert("Player", null, values);
		db.close();
		
		String sql = "SELECT player_id FROM Player;";
		int id = 1;
		
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToLast())
		{
			id = cursor.getInt(0);
		}
		
		return new Player(id, name, 0, null);
	}
}
