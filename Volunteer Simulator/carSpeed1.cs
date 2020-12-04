using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class carSpeed1 : MonoBehaviour
{

    float Speed = 30.0f;
    public GameObject Car;
    public GameObject Reference1;
    public GameObject Reference2;
    int ok = 0;
    static Animator anim;
    static Collider other;




    void Update()
    {
        //Go towards Player's x 
        if (Car.transform.position.x >= Reference2.transform.position.x && ok == 0)
        {
            //Go right 
            transform.position += new Vector3(-Speed * Time.deltaTime, 0, 0);
        }
        else
        {
            ok = 1;
            Car.transform.rotation = Quaternion.Euler(0, 90, 0);
            if (Car.transform.position.x <= Reference1.transform.position.x)
            {
                //Go right 
                transform.position -= new Vector3(-Speed * Time.deltaTime, 0, 0);
            }
            else
            {
                ok = 0;
                Car.transform.rotation = Quaternion.Euler(0, -90, 0);
            }
        }
    }


    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "MainCamera" || other.gameObject.tag == "fan" || other.gameObject.tag == "cop" || other.gameObject.tag == "hurtFan")
        {
            SceneManager.LoadSceneAsync(SceneManager.GetActiveScene().buildIndex);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }
    }


}
