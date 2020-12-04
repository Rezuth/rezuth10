using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class carSpeed2 : MonoBehaviour
{

    float Speed = 25.0f;
    public GameObject Car;
    public GameObject HurtFanFinal;
    public GameObject Reference1;
    public GameObject Reference2;
    public GameObject Reference3;
    int ok = 0;




    void Update()
    {

        if(HurtFanFinal.transform.position.z <= Reference3.transform.position.z)
        {
            if (Car.transform.position.x <= Reference1.transform.position.x)
            {
                //Go right 
                transform.position -= new Vector3(-Speed * Time.deltaTime, 0, 0);
            }

            else
            {
                Car.transform.rotation = Quaternion.Euler(0, 0, 0);
                if (Car.transform.position.z <= Reference2.transform.position.z)
                {
                    //Go right 
                    transform.position -= new Vector3(0, 0, -Speed * Time.deltaTime);
                }
            }
        }

       
    }


   


}
