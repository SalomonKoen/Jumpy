using UnityEngine;
using System.Collections;

public class PlayerCollisionScript : MonoBehaviour {
	public GameObject player;
	
	private PlayerScript script;
	
	private BoxCollider2D box;
	private float boxWidth;

	private static int kills = 0;
	
	void Start ()
	{
		script = player.GetComponent<PlayerScript>();
		box = this.GetComponent<BoxCollider2D>();
		boxWidth = box.size.x*player.transform.localScale.x;
	}

	public static int getKills()
	{
		return kills;
	}

	void OnCollisionEnter2D(Collision2D collision)
	{
		if (script.isMoving())
		{
			if (collision.gameObject.tag == "platform" && gameObject.tag == "platformCollider")
			{
				script.setJump(true);
			}
			else if (collision.gameObject.tag == "enemy" && gameObject.tag == "enemyCollider")
			{
				Transform enemy = collision.gameObject.transform;
				BoxCollider2D collider = collision.gameObject.GetComponent<BoxCollider2D>();
			
				float width = collider.size.x*enemy.localScale.x;
				float height = collider.size.y*enemy.localScale.x;
				
				if ((player.transform.position.x + boxWidth/2 < enemy.position.x + width/2 || player.transform.position.x - boxWidth/2 > enemy.position.x - width/2) && player.transform.position.y >= enemy.position.y + height/2)
				{
					script.setJump(true);
					player.layer = 8;
					player.transform.GetChild(1).gameObject.layer = 8;
					Destroy(collision.gameObject);
				}
				else
				{
					script.setMove(false);
					player.layer = 12;
					player.transform.GetChild(0).gameObject.layer = 12;
					player.transform.GetChild(1).gameObject.layer = 12;

					Destroy(collision.gameObject);
				}

				kills++;
			}
		}
	}
}
