/*using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Level2Fail : MonoBehaviour {


    public GameObject Policeman;
    public GameObject Hooligan;
    static Animator anim;

    // Use this for initialization
    void Start () {

        anim = GetComponent<Animator>();

    }
	
	// Update is called once per frame
	void Update () {

        if (Policeman.transform.position.z <= 100)
        {
            Hooligan.GetComponent<Animator>().Play("Idle_HardDay");
            //Application.LoadLevel(1);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }


    }
}*/

using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Level2Fail : MonoBehaviour
{

    public GameObject textDisplay;
    public int secondsLeft = 50;
    public bool takeAway = false;
    public GameObject Policeman;
    public GameObject Hooligan;
    static Animator anim;
    public bool levelSucc = false;



    // Use this for initialization
    void Start()
    {
        anim = GetComponent<Animator>();
        textDisplay.GetComponent<Text>().text = secondsLeft + " seconds left";

    }

    // Update is called once per frame
    void Update()
    {

        if (takeAway == false && secondsLeft > 0 && levelSucc == false)
        {
            StartCoroutine(TimerTake());
        }
        else
        {
            if(levelSucc == true)
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
            Hooligan.GetComponent<Animator>().Play("Death");
            yield return new WaitForSeconds(5);
            Application.LoadLevel(1);
        }


        if (Policeman.transform.position.z <= 100)
        {
            Hooligan.GetComponent<Animator>().Play("Idle_HardDay");
            levelSucc = true;
            //Application.LoadLevel(1);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }
        
    }

    
}

