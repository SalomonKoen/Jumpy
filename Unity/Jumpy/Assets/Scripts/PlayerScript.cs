using UnityEngine;
using System.Collections;
using Parse;

public class PlayerScript : MonoBehaviour {

	public static bool Pause = false;

    public float jumpForce = 7f;
    public float damp = 20;

    private bool jump = true;
	
	public GameObject bullet;

	public float fireRate = 0.2f;
	private float nextFire = 0.0F;

	private bool move = true;

	private BoxCollider2D box;
	private float boxWidth;

	public static float distance = 0;

    void Start()
    {
		transform.position =  new Vector2(0, renderer.bounds.size.y/2);
		box = this.GetComponent<BoxCollider2D>();
		boxWidth = box.size.x*transform.localScale.x;
    }

	void Update()
	{
		if (Pause)
		{
			rigidbody2D.Sleep();
			return;
		}
		else if (!Pause && rigidbody2D.IsSleeping())
			rigidbody2D.WakeUp();

		if (move)
		{
			if (Input.touchCount > 0 && Time.time > nextFire)
			{
				nextFire = Time.time + fireRate;
				Vector2 direction = (Input.GetTouch(0).position - new Vector2(Screen.width / 2, 0)).normalized;
				
				GameObject obj = (GameObject)Instantiate(bullet, new Vector2(transform.position.x, transform.position.y + renderer.bounds.size.y - bullet.renderer.bounds.size.y), Quaternion.identity);
				BulletScript script = obj.GetComponent<BulletScript>();
				script.setDirection(direction);
				script.Move();
			}

			if (Application.platform != RuntimePlatform.Android)
			{
				if (Input.GetButtonDown("Fire1"))
				{
					nextFire = Time.time + fireRate;
					Vector3 direction = (Input.mousePosition - new Vector3(Screen.width / 2, 0)).normalized;
					
					GameObject obj = (GameObject)Instantiate(bullet, new Vector2(transform.position.x, transform.position.y + renderer.bounds.size.y - bullet.renderer.bounds.size.y), Quaternion.identity);
					BulletScript script = obj.GetComponent<BulletScript>();
					script.setDirection(direction);
					script.Move();
				}
			}
		}
	}

    void FixedUpdate()
    {
		if (Pause)
			return;


		if (move)
		{
			if (Application.platform != RuntimePlatform.Android)
			{
				if (Input.GetKey (KeyCode.LeftArrow))
				{
					rigidbody2D.velocity = new Vector2(-4, rigidbody2D.velocity.y);
				}
				else if (Input.GetKey (KeyCode.RightArrow))
				{
					rigidbody2D.velocity = new Vector2(4, rigidbody2D.velocity.y);
				}
				else
				{
					rigidbody2D.velocity = new Vector2(0, rigidbody2D.velocity.y);
				}
			}

	        float x = Input.acceleration.x;

	        transform.position = new Vector3(Mathf.Lerp(transform.position.x, transform.position.x + x, Time.deltaTime * damp), transform.position.y, -1);

	        if (jump)
	        {
	            rigidbody2D.velocity = new Vector2(rigidbody2D.velocity.x, jumpForce);
	            gameObject.layer = 8;
	            jump = false;
	        }

	        if (rigidbody2D.velocity.y <= 0)
	        {
	            gameObject.layer = 9;
	        }

			float width = Camera.main.ScreenToWorldPoint(new Vector2(Camera.main.pixelWidth,0)).x;
			
			if (transform.position.x < -width)
			{
				transform.position = new Vector2(width, transform.position.y);
			}
			else if (transform.position.x > width)
			{
				transform.position = new Vector2(-width, transform.position.y);
			}

			if (transform.position.y < 0)
			{
				GameObject c = GameObject.Find("Main Camera");
				c.AddComponent("ShowSummary");

				Time.timeScale = 0;

					
				ParseObject scoreObject = new ParseObject("Highscore");
				scoreObject["UserName"] = "Martin";
				scoreObject["UserScore"] = Mathf.Ceil(distance/10);
				scoreObject.SaveAsync();
			}
		}
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
		if (move)
		{
			if (collision.gameObject.tag == "platform")
			{
	        	jump = true;
			}
			else if (collision.gameObject.tag == "enemy")
			{
				Transform enemy = collision.gameObject.transform;
				BoxCollider2D collider = collision.gameObject.GetComponent<BoxCollider2D>();

				float width = collider.size.x*enemy.localScale.x;
				float height = collider.size.y*enemy.localScale.y;

				if (transform.position.x + boxWidth/2 < enemy.position.x + width/2 && transform.position.x - boxWidth/2 > enemy.position.x - width/2 && transform.position.y >= enemy.position.y + height/2)
				{
					jump = true;
					Destroy(collision.gameObject);
				}
				else
				{
					move = false;
					gameObject.layer = 12;
					Destroy(collision.gameObject);
				}
			}
		}
    }
}
