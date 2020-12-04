


using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class CarCrash : MonoBehaviour
{

    float Speed = 20.0f;
    public GameObject CarCrashed;
    public GameObject hurtfan;
    public GameObject Reference1;
    public GameObject Reference2;




    void Start()
    {
        GameObject.Find("man-skate (15)").transform.localScale = new Vector3(0, 0, 0);
    }

    void Update()
    {
        //Go towards Player's x 


        if (CarCrashed.transform.position.x <= Reference1.transform.position.x)
        {
            //Go right 
            transform.position -= new Vector3(-Speed * Time.deltaTime, 0, 0);
        }

        else
        {
            CarCrashed.transform.rotation = Quaternion.Euler(0, 135.0f, 0);
            if (CarCrashed.transform.position.x <= Reference2.transform.position.x)
            {
                //Go right 
                transform.position -= new Vector3(-Speed * Time.deltaTime, 0, Speed * Time.deltaTime);
            }

            else
            {
                Invoke("SpawnHurtFan", 0.5f);
            }
        }
    }

    void SpawnHurtFan()
    {
        GameObject.Find("man-skate (15)").transform.localScale = new Vector3(1, 1, 1);
        hurtfan.GetComponent<Animator>().Play("Death");
    }
}



