using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class TimerFinal : MonoBehaviour
{

    public GameObject textDisplay;
    public GameObject CarFinal;
    public GameObject CheckPoint;
    public int secondsLeft = 90;
    public bool takeAway = false;
    public bool levelSucc = false;
    



    // Use this for initialization
    void Start()
    {

        textDisplay.GetComponent<Text>().text = secondsLeft + " seconds left";

    }

    // Update is called once per frame
    void Update()
    {


        bool k1 = Input.GetKeyDown(KeyCode.LeftShift);
        bool k2 = Input.GetKeyDown(KeyCode.Space);
        if (Input.GetKeyDown(KeyCode.LeftShift) || Input.GetKeyDown(KeyCode.Space))
        {
            secondsLeft = secondsLeft - 20;
        }

        if (takeAway == false && secondsLeft > 0 && levelSucc == false)
        {

            
                StartCoroutine(TimerTake());

        }
        else
        {
            if (levelSucc == true)
            {
                textDisplay.GetComponent<Text>().text = "Level finished";
            }
        }

    }

    IEnumerator TimerTake()
    {
       
        takeAway = true;
        yield return new WaitForSeconds(1.0f);
        secondsLeft = secondsLeft - 1;
        textDisplay.GetComponent<Text>().text = secondsLeft + " seconds left";
        takeAway = false;

        

        if (secondsLeft <= 0)
        {
            SceneManager.LoadSceneAsync(SceneManager.GetActiveScene().buildIndex);
        }

        if (CarFinal.transform.position.x < CheckPoint.transform.position.x)
        {
            levelSucc = true;
        } 
    }

    

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "MainCamera")
        {
            secondsLeft = secondsLeft - 20;
            if (secondsLeft <= 0)
            {
                SceneManager.LoadSceneAsync(SceneManager.GetActiveScene().buildIndex);
            }
        }


       

        
    }

   


}
