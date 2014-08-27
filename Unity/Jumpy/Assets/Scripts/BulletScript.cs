using UnityEngine;
using System.Collections;

public class BulletScript : MonoBehaviour
{
	public float speed = 5;
	private Vector2 direction = new Vector2(0,0);
	public int damage = 1;

	void Start ()
	{
		
	}

	void Update()
	{
		if (!this.renderer.IsVisibleFrom(Camera.main))
		{
			Destroy (this.gameObject);
		}
	}

	public void Move()
	{
		rigidbody2D.velocity = new Vector2(Mathf.Clamp(direction.x, -5, 5), Mathf.Clamp (direction.y, 1, 5)) * speed;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public int getDamage()
	{
		return damage;
	}

	void OnCollisionEnter2D(Collision2D collision)
	{
		if (collision.gameObject.tag == "enemy")
		{
			Destroy(this.gameObject);

			EnemyScript script = collision.gameObject.GetComponent<EnemyScript>();

			if (script != null)
			{
				script.Damage (damage);
			}
		}
	}
}
