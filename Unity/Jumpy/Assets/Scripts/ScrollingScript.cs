using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class ScrollingScript : MonoBehaviour
{
	public static Vector2 speed = new Vector2(0, 0);

    public float speedMultiplier = 1f;
	public Vector2 direction = new Vector2(0, -1);

	public bool isLooping = false;
	public bool isLinkedToCamera = false;

    private bool firstUp = false;
    private Vector2 firstPos = Vector2.zero;

    public static bool paused = false;

	private List<Transform> backgroundPart;

	void Start()
	{
		if (isLooping)
		{
			backgroundPart = new List<Transform>();

			for (int i = 0 ; i < transform.childCount; i++)
			{
				Transform child = transform.GetChild(i);

				if (child.renderer != null)
				{
					backgroundPart.Add(child);
				}

                Vector3 dirNorm = direction.normalized;

                if (dirNorm.x == 1)
                {
                    backgroundPart = backgroundPart.OrderByDescending(t => t.position.x).ToList();
                }
                else if (dirNorm.x == -1)
                {
                    backgroundPart = backgroundPart.OrderBy(t => t.position.x).ToList();
                }
                else if (dirNorm.y == 1)
                {
                    backgroundPart = backgroundPart.OrderByDescending(t => t.position.y).ToList();
                }
                else if (dirNorm.x == -1)
                {
                    backgroundPart = backgroundPart.OrderBy(t => t.position.y).ToList();
                }

			}
		}
	}

	void FixedUpdate()
	{
        if (!paused)
        {
            if (!isLinkedToCamera)
            {
                Vector3 movement = new Vector3(speed.x * direction.x, speed.y * direction.y, 0);
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

    private void Loop(Transform firstChild, Vector3 pos)
    {
        firstChild.position = pos;

        backgroundPart.Remove (firstChild);
        backgroundPart.Add (firstChild);
    }
}
