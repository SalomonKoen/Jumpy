using UnityEngine;
using System.Collections;

public class HUDScript : MonoBehaviour {

	public Texture btnTexture;
	public Texture btnTexture2;
	public Texture btnTexture3;
	public Texture btnTexture4;
	public Texture btnTexture5;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnGUI () {
		GUI.contentColor = Color.black;
		GUI.Label(new Rect(10,10,100,90), "Distance: " + Mathf.Ceil(PlayerScript.distance/10));

		// Make a background box
		//GUI.Box(new Rect(10,10,100,90), "Loader Menu");

		// Make the first button. If it is pressed, Application.Loadlevel (1) will be executed
		if(GUI.Button(new Rect(20,40,80,20), "PAUSE")) {
			PlayerScript.Pause = !PlayerScript.Pause;
		}

		if (!btnTexture) {
			if (GUI.Button(new Rect(10, Screen.height-50, 50, 30), "Click"))
			{
			}
		}
		else if (GUI.Button(new Rect(10, Screen.height-50, 50, 50), btnTexture))
		{
		}		
	}
}
