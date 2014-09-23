using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class ScrollingScript : MonoBehaviour
{
	public static Vector2 speed = new Vector2(0, 0);

	private static float height = 0;

    public float speedMultiplier = 1f;
	public Vector2 direction = new Vector2(0, -1);

	public bool isLooping = false;
	public bool isLinkedToCamera = false;

    private bool firstUp = false;
    private Vector2 firstPos = Vector2.zero;

    public static bool paused = false;
	public bool fillScreen = false; 

	public float margin = 0f;
	public bool alignLeft = false;
	public bool alignRight = false;

	private List<Transform> backgroundPart;

	void Start()
	{
		if (isLooping)
		{
			backgroundPart = new List<Transform>();

			float pos = 0;
			float screenWidth = Camera.main.ScreenToWorldPoint(new Vector2(Camera.main.pixelWidth, 0)).x*2;
			float screenHeight = Camera.main.ScreenToWorldPoint(new Vector2(0, Camera.main.pixelHeight)).y;
			Vector3 dirNorm = direction.normalized;

			for (int i = 0 ; i < transform.childCount; i++)
			{
				Transform child = transform.GetChild(i);

				float width = child.renderer.bounds.size.x;
				float height = child.renderer.bounds.size.y;

				if (child.renderer != null)
				{
					backgroundPart.Add(child);
				}

                if (dirNorm.x == 1)
                {
					float scale = screenHeight/height;
					
					if (fillScreen)
					{
						child.localScale = new Vector3(child.localScale.x * scale, child.localScale.y * scale);
					}

                    backgroundPart = backgroundPart.OrderByDescending(t => t.position.x).ToList();
					pos += width*scale + 1;
                }
                else if (dirNorm.x == -1)
                {
					float scale = screenHeight/height;
					
					if (fillScreen)
					{
						child.localScale = new Vector3(child.localScale.x * scale, child.localScale.y * scale);
					}

                    backgroundPart = backgroundPart.OrderBy(t => t.position.x).ToList();
					pos += width*scale + 1;
                }
                else if (dirNorm.y == 1)
                {
					float scale = screenWidth/width;
					
					if (fillScreen)
					{
						child.localScale = new Vector3(child.localScale.x * scale, child.localScale.y * scale);
					}

					backgroundPart = backgroundPart.OrderByDescending(t => t.position.x).ToList();
					pos += height*scale + 1;
                }
                else if (dirNorm.y == -1)
                {
					float scale = screenWidth/width;

					if (fillScreen)
					{
						child.localScale = new Vector3(child.localScale.x * scale, child.localScale.y * scale);
						child.position = new Vector3(0, height*scale/2 + pos, child.position.z);
						pos += height*scale;
					}
					else
					{
						if (alignLeft)
						{
							child.position = new Vector3(-screenWidth/2 + width/2 - margin, height/2 + pos, child.position.z);
						}
						else if (alignRight)
						{
							child.position = new Vector3(screenWidth/2 - width/2 + margin, height/2 + pos, child.position.z);
						}
						else
							child.position = new Vector3(child.position.x, height/2 + pos, child.position.z);

						pos += height;
					}
                }
			}

			if (dirNorm.y == -1)
			{
				while (pos < 2*screenHeight)
				{
					Transform last = backgroundPart.Last<Transform>();
					Transform t = (Transform)Instantiate(last, new Vector3(0, 0), Quaternion.identity);
					float height = t.renderer.bounds.size.y;
					t.position = new Vector3(0, height/2 + pos, last.position.z);
					t.parent = transform;
					pos += height;

					backgroundPart.Add(t);
				}

				backgroundPart = backgroundPart.OrderByDescending(t => t.position.x).ToList();
			}
		}
	}

	void FixedUpdate()
	{
        if (!paused)
        {
            if (!isLinkedToCamera)
            {
				Vector3 movement = new Vector3(speed.x * direction.x * speedMultiplier, speed.y * direction.y * speedMultiplier, 0);
                movement *= Time.deltaTime;

                transform.Translate(movement);
            }
            else if (isLinkedToCamera && Camera.main.WorldToScreenPoint(transform.position).y > Camera.main.pixelHeight / 2 && rigidbody2D.velocity.y > 0)
		    {
                if (!firstUp)
                {
                    firstUp = true;
                    firstPos = transform.position;
                }

                speed = new Vector2(0, rigidbody2D.velocity.y);

                transform.position = new Vector3(transform.position.x, firstPos.y, transform.position.z);

				height += 0.1f;
		    }
            else if (isLinkedToCamera && rigidbody2D.velocity.y <= 0)
            {
                speed = new Vector2(0, 0);
                firstUp = false;
            }

            if (isLooping)
            {
                Transform firstChild = backgroundPart.FirstOrDefault();

                if (firstChild != null)
                {
                    Vector3 dirNorm = direction.normalized;

                    if (dirNorm.x == 1 && firstChild.position.x > Camera.main.transform.position.x && !firstChild.renderer.IsVisibleFrom(Camera.main))
                    {
                        Transform lastChild = backgroundPart.LastOrDefault();
                        Vector3 lastPosition = lastChild.transform.position;
                        Vector3 lastSize = (lastChild.renderer.bounds.max - lastChild.renderer.bounds.min);
                        Loop(firstChild, new Vector3(lastPosition.x - lastSize.x, firstChild.position.y, firstChild.position.z));
                    }
                    else if (dirNorm.x == -1 && firstChild.position.x < Camera.main.transform.position.x && !firstChild.renderer.IsVisibleFrom(Camera.main))
                    {
                        Transform lastChild = backgroundPart.LastOrDefault();
                        Vector3 lastPosition = lastChild.transform.position;
                        Vector3 lastSize = (lastChild.renderer.bounds.max - lastChild.renderer.bounds.min);
                        Loop(firstChild, new Vector3(lastPosition.x + lastSize.x, firstChild.position.y, firstChild.position.z));
                    }
                    else if (dirNorm.y == 1 && firstChild.position.y > Camera.main.transform.position.y && !firstChild.renderer.IsVisibleFrom(Camera.main))
                    {
                        Transform lastChild = backgroundPart.LastOrDefault();
                        Vector3 lastPosition = lastChild.transform.position;
                        Vector3 lastSize = (lastChild.renderer.bounds.max - lastChild.renderer.bounds.min);
                        Loop(firstChild, new Vector3(firstChild.position.x, lastPosition.y - lastSize.y, firstChild.position.z));
                    }
                    else if (dirNorm.y == -1 && firstChild.position.y < Camera.main.transform.position.y && !firstChild.renderer.IsVisibleFrom(Camera.main))
                    {
                        Transform lastChild = backgroundPart.LastOrDefault();
                        Vector3 lastPosition = lastChild.transform.position;
                        Vector3 lastSize = (lastChild.renderer.bounds.max - lastChild.renderer.bounds.min);
                        Loop(firstChild, new Vector3(firstChild.position.x, lastPosition.y + lastSize.y, firstChild.position.z));
                    }
                }
            }
		}
	}

	public static int getHeight()
	{
		return (int)height;
	}

    private void Loop(Transform firstChild, Vector3 pos)
    {
        firstChild.position = pos;

        backgroundPart.Remove (firstChild);
        backgroundPart.Add (firstChild);
    }
}
