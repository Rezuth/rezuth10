


using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TreeFalling : MonoBehaviour
{

    float Speed = 20.0f;
    public GameObject Tree;
    public GameObject Player;
    public GameObject Reference;




   

    void Update()
    {
        if(Player.transform.position.z <= Reference.transform.position.z)
        {
            Tree.transform.position = new Vector3(130.25f, 1.33f, 139.7f);
            Tree.transform.rotation = Quaternion.Euler(1.712f, 183.242f, 263.142f);
        }
    }

}



