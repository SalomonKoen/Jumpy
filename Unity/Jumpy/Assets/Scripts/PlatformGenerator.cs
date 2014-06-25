using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class PlatformGenerator : MonoBehaviour
{
    public GameObject player;
    public List<GameObject> platforms = new List<GameObject>();

    private GameObject curPlatform;
    private float area = 1f;

    private Vector2 size;
    public GameObject maxObj = null;

	void Start()
    {
        curPlatform = platforms[0];
        size = Camera.main.ScreenToWorldPoint(new Vector2(Camera.main.pixelWidth, Camera.main.pixelHeight));

        float fourth = size.x / 2;
        float cur = -fourth;
        for (int i = 1; i <= 3; i++)
        {
            Instantiate(curPlatform, new Vector3(cur, curPlatform.renderer.bounds.size.y), Quaternion.identity);
            cur += fourth;
        }

        float curPoint = curPlatform.renderer.bounds.size.y;

        while (true)
        {
            curPoint += Random.Range(0, area) + curPlatform.renderer.bounds.size.y;

            if (curPoint > size.y)
                break;

           maxObj = (GameObject)Instantiate(curPlatform, new Vector3(Random.Range(-size.x+curPlatform.renderer.bounds.size.x/2, size.x-curPlatform.renderer.bounds.size.x/2), curPoint), Quaternion.identity);
        }

        spawnPlatforms();
	}
	
	void Update ()
    {
        if (maxObj.transform.position.y < size.y)
            spawnPlatforms();
	}

    public void spawnPlatforms()
    {
        float curPoint = size.y;

        while (true)
        {
            curPoint += Random.Range(0, area) + curPlatform.renderer.bounds.size.y;
            
            if (curPoint > size.y*2)
                break;

            maxObj = (GameObject)Instantiate(curPlatform, new Vector3(Random.Range(-size.x + curPlatform.renderer.bounds.size.x / 2, size.x - curPlatform.renderer.bounds.size.x / 2), curPoint), Quaternion.identity);
        }
    }
}
