using UnityEngine;
using System.Collections;

public class EnemyScript : MonoBehaviour
{ 
	public int health = 1;

	void Start ()
	{
		
	}
	
	void Update ()
    {
		if (transform.parent.position.y + renderer.bounds.size.y/2 < 0)
		{
			Destroy (this.transform.parent.gameObject);
		}
	}

	public void Damage(int damage)
	{
		health -= damage;

		if (health <= 0)
		{
			Destroy (this.transform.parent.gameObject);
		}
	}

	void OnCollisionEnter2D(Collision2D collision)
	{
		if (collision.gameObject.tag == "enemy")
		{
			Destroy (this);
		}
	}
}
