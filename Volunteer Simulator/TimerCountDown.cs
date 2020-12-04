using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class TimerCountDown : MonoBehaviour {

    public GameObject textDisplay;
    public GameObject fan1;
    public GameObject fan2;
    public GameObject fan3;
    public GameObject fan4;
    public int secondsLeft = 20;
    public bool takeAway = false;
    public bool levelSucc = false;

 

	// Use this for initialization
	void Start () {

        textDisplay.GetComponent<Text>().text = secondsLeft + " seconds left";

    }
	
	// Update is called once per frame
	void Update () {

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
        yield return new WaitForSeconds(1.7f);
        secondsLeft = secondsLeft - 1;
        textDisplay.GetComponent<Text>().text = secondsLeft + " seconds left";
        takeAway = false;

        if (secondsLeft <= 0)
        {
            SceneManager.LoadSceneAsync(SceneManager.GetActiveScene().buildIndex);
        }
       
        if (fan1.transform.position.x >= 138 || fan2.transform.position.x >= 138 || fan3.transform.position.x >= 138 || fan4.transform.position.x >= 138)
        {
            levelSucc = true;
            //Application.LoadLevel(1);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }
    }

    /*void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "MainGuy")
        {
            secondsLeft = secondsLeft - 5;
        }
    }*/

   
}
