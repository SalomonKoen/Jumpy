using UnityEngine;
using System.Collections;

public class ShowSummary : MonoBehaviour {

	// Use this for initialization
	void Start () {
		FB.Init(null);
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnGUI () {
		// Make a background box
		GUI.Box(new Rect(10,10,Screen.width-20,Screen.height-20), "Summary");

		GUIStyle centeredTextStyle = new GUIStyle("label");
		centeredTextStyle.alignment = TextAnchor.MiddleCenter;
		GUI.Label(new Rect(0,50,Screen.width,100), "Kills: ",centeredTextStyle);

		GUI.Label(new Rect(0,50,Screen.width,150), "Distance: " + PlayerScript.distance,centeredTextStyle);


		//GUI.Label(new Rect(50,50,100,100),"Kills: ");
		
		// Make the first button. If it is pressed, Application.Loadlevel (1) will be executed
		if(GUI.Button(new Rect(20,40,Screen.width-40,20), "Restart")) {
			PlayerScript.distance = 0;
			Application.LoadLevel(0);
		}

		if (!FB.IsLoggedIn)                                                                                              
		{                                                          
			FB.
			GUI.Label((new Rect(179 , 11, 287, 160)), "Login to Facebook");             
			if (GUI.Button(new Rect(179 , 11, 287, 160), ""))                                      
			{                                                                                                            
				FB.Login("email,publish_actions", LoginCallback);                                                        
			}                                                                                                            
		} 
		
		// Make the second button.
		if(GUI.Button(new Rect(20,70,Screen.width-40,20), "Share")) {
			if (FB.IsLoggedIn)
			{
				FB.Feed(                                                                                                                 
				        linkCaption: "I just smashed " + 0 + " friends! Can you beat it?",               
				        picture: "http://www.friendsmash.com/images/logo_large.jpg",                                                     
				        linkName: "Checkout my Friend Smash greatness!",                                                                 
				        link: "http://apps.facebook.com/" + FB.AppId + "/?challenge_brag=" + (FB.IsLoggedIn ? FB.UserId : "guest")       
				        );     
			}
		}

		if(GUI.Button(new Rect(20,150,Screen.width-40,20), "Quick Buy")) {
			//
		}
	}

	void LoginCallback(FBResult result)                                                        
	{                                                                                                                                                
		
		if (FB.IsLoggedIn)                                                                     
		{                                                                                      
			OnLoggedIn();                                                                      
		}                                                                                      
	}                                                                                          
	
	void OnLoggedIn()                                                                          
	{                                                                                                                                    
	}  
}
