using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class f4 : MonoBehaviour
{

    public GameObject Player;
    public float TargetDistance;
    public float AllowedDistance = 3;
    public GameObject Policeman;
    public GameObject Checkpoint;
    public float FollowSpeed;
    public RaycastHit Shot;
    static Animator anim;
    int ok = 0;

    void Start()
    {
        anim = GetComponent<Animator>();
        //GameObject.Find("man-police (4)").transform.localScale = new Vector3(0, 0, 0);


    }

    // Update is called once per frame

    void Update()
    {
  
        if(Player.transform.position.z >= Checkpoint.transform.position.z)
        {

            //GameObject.Find("man-police (4)").transform.localScale = new Vector3(1, 1, 1);
            anim.SetBool("isArrived", true);
            ok = 1;
        }

        if(ok == 1 && Policeman.transform.position.z > 100)
        {
            transform.LookAt(Player.transform);
            if (Physics.Raycast(transform.position, transform.TransformDirection(Vector3.forward), out Shot))
            {
                TargetDistance = Shot.distance;
                if (TargetDistance >= AllowedDistance)
                {
                    FollowSpeed = 0.09f;
                    anim.SetBool("isRunning", true);
                    Policeman.GetComponent<Animator>().Play("Run_InPlace");
                    transform.position = Vector3.MoveTowards(transform.position, Player.transform.position, FollowSpeed);
                }
                else
                {
                    FollowSpeed = 0;
                    anim.SetBool("isRunning", false);
                    Policeman.GetComponent<Animator>().Play("Idle_Generic");
                }
            }
            //Policeman.SetActive(false);
        }

        if (Policeman.transform.position.z <= 100)
        {
            anim.SetBool("isFightStopped", true);
            Policeman.GetComponent<Animator>().Play("Idle_Texting");
            Invoke("ChangeLevel", 5.0f);
            //Application.LoadLevel(1);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }

        
    }

    void ChangeLevel()
    {
        Application.LoadLevel(2);
    }
}
