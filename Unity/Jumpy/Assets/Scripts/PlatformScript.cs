using UnityEngine;
using System.Collections;

public class PlatformScript : MonoBehaviour {

	void Update () {
	    if (transform.position.y < 0)
        {
            Destroy(gameObject);
        }
	}
}
