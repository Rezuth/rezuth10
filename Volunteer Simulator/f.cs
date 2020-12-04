using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class f : MonoBehaviour
{

    public GameObject Player;
    public float TargetDistance;
    public float AllowedDistance = 3;
    public GameObject FootballFan;
    public float FollowSpeed;
    public RaycastHit Shot;
    static Animator anim;
    public GameObject ReferenceStadium;
    public bool isWalking = false;

    void Start()
    {
        anim = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {

        transform.LookAt(Player.transform);
        if (Physics.Raycast(transform.position, transform.TransformDirection(Vector3.forward), out Shot))
        {
            TargetDistance = Shot.distance;
            if (TargetDistance >= AllowedDistance)
            {
                FollowSpeed = 0.095f;
                isWalking = true;
                FootballFan.GetComponent<Animator>().Play("Walk_Male");
                transform.position = Vector3.MoveTowards(transform.position, Player.transform.position, FollowSpeed);
            }
            else
            {
                FollowSpeed = 0;
                isWalking = false;
                //FootballFan.GetComponent<Animator>().Play("Walk_Male");
            }

            
        }

        if (FootballFan.transform.position.x >= 138)
        {
            Invoke("ChangeLevel", 5.0f);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }

    }

    void ChangeLevel()
    {
        Application.LoadLevel(1);
    }
}
