using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {

    public float jumpForce = 7f;
    public float damp = 20;

    private bool jump = true;

    void Start()
    {
    }

    void FixedUpdate()
    {
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
    }

    void OnCollisionEnter2D(Collision2D col)
    {
        jump = true;
    }

    /*public Vector2 speed = new Vector2(50, 50);

    private Vector2 movement;
	
    void Update()
    {
        float inputX = Input.GetAxis("Horizontal");
        float inputY = Input.GetAxis("Vertical");

        movement = new Vector2(speed.x * inputX, speed.y * inputY);

		var dist = (transform.position - Camera.main.transform.position).z;
		var leftBorder = Camera.main.ViewportToWorldPoint(new Vector3(0, 0, dist)).x;
		var rightBorder = Camera.main.ViewportToWorldPoint(new Vector3(1, 0, dist)).x;
		var topBorder = Camera.main.ViewportToWorldPoint(new Vector3(0, 0, dist)).y;
		var bottomBorder = Camera.main.ViewportToWorldPoint(new Vector3(0, 1, dist)).y;

		transform.position = new Vector3(Mathf.Clamp(transform.position.x, leftBorder + 1.1f, rightBorder - 1.1f),Mathf.Clamp(transform.position.y, topBorder + 1.1f, bottomBorder - 1.1f),transform.position.z);
        
    }

    void FixedUpdate()
    {
        rigidbody2D.velocity = movement;
    }*/
}
