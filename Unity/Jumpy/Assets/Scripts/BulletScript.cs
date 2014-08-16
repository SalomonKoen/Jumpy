using UnityEngine;
using System.Collections;

public class BulletScript : MonoBehaviour
{
	public float speed = 5;
	public Vector2 direction = new Vector2(0,0);

	void Start ()
	{
		
	}

	void Update()
	{

	}

	public void Move()
	{
		rigidbody2D.velocity = new Vector2(Mathf.Clamp(direction.x, -5, 5), Mathf.Clamp (direction.y, 1, 5)) * speed;
	}
}
