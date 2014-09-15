using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {
	public static bool Pause = false;

    public float jumpForce = 7f;
    public float damp = 20;

    private bool jump = true;
	
	public GameObject bullet;

	public float fireRate = 0.2f;
	private float nextFire = 0.0F;

    void Start()
    {
		transform.position =  new Vector2(0, renderer.bounds.size.y/2);
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

    void FixedUpdate()
    {
		if (Pause)
			return;

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
			Time.timeScale = 0;
		}
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
		if (collision.gameObject.tag == "platform")
		{
        	jump = true;
		}
		else if (collision.gameObject.tag == "enemy")
		{
			Destroy(this);
		}
    }
}
