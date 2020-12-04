using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SaveOperation : MonoBehaviour
{

    public GameObject Player;
    public float TargetDistance;
    public float AllowedDistance = 3;
    public GameObject hFan;
    public GameObject Checkpoint;
    public float FollowSpeed;
    public RaycastHit Shot;
    static Animator anim;
    int ok = 0;

    // Update is called once per frame

    void Update()
    {

        if (Player.transform.position.x <= Checkpoint.transform.position.x)
        {  
            ok = 1;
        }

        if (ok == 1 && hFan.transform.position.z > 100)
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
            //Policeman.SetActive(false);
        }

        




    }

    
}
