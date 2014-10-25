using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class SetupScript : MonoBehaviour {

	public GameObject player;
	public GameObject gameGenerator;

	void Start ()
    {
        Screen.sleepTimeout = SleepTimeout.NeverSleep;

		/*AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
		AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

		AndroidJavaObject list = activity.Call<AndroidJavaObject>("getPowerups");

		int n = list.Call<int>("size");

		Powerup[] powerups = new Powerup[n];

		for (int i = 0; i < n; i++)
		{
			AndroidJavaObject p = list.Call<AndroidJavaObject>("get", i);

			Powerup powerup = new Powerup(p.Call<int>("getQuantity"), p.Call<int>("getType"), p.Call<int>("getValue"));
			powerups[i] = powerup;
		}

		player.GetComponent<PlayerScript>().setPowerups(powerups);

		AndroidJavaObject highscoreList = activity.Call<AndroidJavaObject>("getHighScores");
		
		int m = highscoreList.Call<int>("size");
		
		List<HighScore> highscores = new List<HighScore>();
		
		for (int i = 0; i < n; i++)
		{
			AndroidJavaObject h = highscoreList.Call<AndroidJavaObject>("get", i);
			
			HighScore highscore = new HighScore(h.Call<string>("getName"), h.Call<int>("getHeight"));
			highscores.Add(highscore);
		}

		gameGenerator.GetComponent<GameGenerator>().setHighscores(highscores);*/
	}
}
