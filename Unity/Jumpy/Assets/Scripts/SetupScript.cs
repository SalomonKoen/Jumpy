using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class SetupScript : MonoBehaviour {

	public GameObject player;
	private List<Powerup> powerups = new List<Powerup>();

	void Start ()
    {
        Screen.sleepTimeout = SleepTimeout.NeverSleep;

		AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
		AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

		AndroidJavaObject list = activity.Call<AndroidJavaObject>("getPowerups");

		int n = list.Call<int>("size");

		for (int i = 0; i < n; i++)
		{
			AndroidJavaObject p = list.Call<AndroidJavaObject>("get", i);

			Powerup powerup = new Powerup(p.Call<int>("getQuantity"), p.Call<int>("getType"), p.Call<int>("getValue"));
			powerups.Add(powerup);
		}
	}
}
