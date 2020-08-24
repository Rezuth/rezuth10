using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class carSpeedFinal : MonoBehaviour
{

    float Speed = 25.0f;
    public GameObject Car;
    public GameObject PoliceReference;
    public GameObject Police;
    public GameObject Reference1;
    public GameObject Reference2;
    int ok = 0;




    void Update()
    {
        if(Police.transform.rotation == PoliceReference.transform.rotation)
        {
            if (Car.transform.position.z >= Reference1.transform.position.z)
            {
                //Go right 
                transform.position += new Vector3(0, 0, -Speed * Time.deltaTime);
            }

            else
            {
                Car.transform.rotation = Quaternion.Euler(0, 270, 0);
                if (Car.transform.position.x >= Reference2.transform.position.x)
                {
                    //Go right 
                    transform.position += new Vector3(-Speed * Time.deltaTime, 0, 0);
                }
                else
                {
                    Invoke("EndGame", 5.0f);
                }
            }
        }
    }

    void EndGame()
    {
        Application.LoadLevel(0);
    }
}
