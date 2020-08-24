using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class c3 : MonoBehaviour {

    public GameObject Reference;
    public GameObject Player;
    public GameObject fan;

    void Update()
    {
        if (Player.transform.position.z <= Reference.transform.position.z)
        {
            fan.transform.position = Reference.transform.position;
        }
    }
    

}
