using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityStandardAssets.Characters.FirstPerson;

public class SaveOperation2 : MonoBehaviour
{
    public GameObject Player;
    public float TargetDistance;
    public float AllowedDistance = 3;
    public GameObject hFan;
    public GameObject Checkpoint;
    public GameObject Checkpoint2;
    public GameObject PolicemanFinal;
    public float FollowSpeed;
    public RaycastHit Shot;
    static Animator anim;
    int ok = 0;

    // Update is called once per frame

    void Start()
    {
        anim = GetComponent<Animator>();
        GameObject.Find("man-skate (16)").transform.localScale = new Vector3(0, 0, 0);
        hFan.GetComponent<Animator>().Play("Death");
    }

    void Update()
    {

        if(Player.transform.position.z <= Checkpoint.transform.position.z)
        {
            GameObject.Find("man-skate (16)").transform.localScale = new Vector3(1, 1, 1);
            ok = 1;
        }

        if (ok == 1 && hFan.transform.position.z > Checkpoint2.transform.position.z)
        {
            transform.LookAt(Player.transform);
            if (Physics.Raycast(transform.position, transform.TransformDirection(Vector3.forward), out Shot))
            {
                TargetDistance = Shot.distance;
                if (TargetDistance >= AllowedDistance)
                {
                    FollowSpeed = 0.2f;
                    transform.position = Vector3.MoveTowards(transform.position, Player.transform.position, FollowSpeed);
                }
                else
                {
                    FollowSpeed = 0;
                }

            }
        }

        if(hFan.transform.position.z <= Checkpoint2.transform.position.z)
        {
            PolicemanFinal.transform.rotation = Quaternion.Euler(0, 0, 0);
            anim.SetBool("isArrivedFinal", true);
            PolicemanFinal.GetComponent<Animator>().Play("Idle_Texting");
        }






    }

   


}