using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class c4 : MonoBehaviour {

    static Animator anim;

    void Start()
    {
        anim = GetComponent<Animator>();

    }

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "car")
        {
            anim.SetBool("isDead7", true);
            (other.GetComponent("f3") as MonoBehaviour).enabled = false;
        }
    }
}
