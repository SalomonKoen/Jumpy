using UnityEngine;
using System.Collections;

public class ScoreScript : MonoBehaviour {
	
	private bool fade = false;

	void Start () {
		transform.localScale = new Vector3(transform.localScale.x*Camera.main.ScreenToWorldPoint(new Vector3(Camera.main.pixelWidth, 0)).x*2/renderer.bounds.size.x, transform.localScale.y, transform.localScale.z);
		transform.GetChild(0).localScale = new Vector3(transform.GetChild(0).localScale.x/transform.localScale.x, transform.GetChild(0).localScale.y, transform.GetChild(0).localScale.z);
	}

	void Update () {
		if (fade)
		{
			fadeAnim();
		}
		else if (transform.position.y < PlayerScript.getTransform().position.y)
		{
			fade = true;
		}
	}

	private void fadeAnim()
	{
		transform.renderer.material.color = Color.Lerp(transform.renderer.material.color, new Color(transform.renderer.material.color.r, transform.renderer.material.color.g, transform.renderer.material.color.b, 0f), Time.deltaTime*3f);
		transform.GetChild(0).renderer.material.color = Color.Lerp(transform.GetChild(0).renderer.material.color, new Color(transform.GetChild(0).renderer.material.color.r, transform.GetChild(0).renderer.material.color.g, transform.GetChild(0).renderer.material.color.b, 0f), Time.deltaTime*3f);

		if (transform.renderer.material.color.a <= 0.01f)
		{
			Destroy(gameObject);
		}
	}
}
