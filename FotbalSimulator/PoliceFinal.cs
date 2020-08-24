using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PoliceFinal : MonoBehaviour
{
    float Speed = 25.0f;
    public GameObject PlayerFinal;
    public GameObject PolicemanFinal;
    public GameObject CheckpointFinal;
    public GameObject CarFinal;
    public GameObject Reference1Final;
    public GameObject Reference2Final;
    static Animator anim;
    int ok = 0;

    void Start()
    {
        anim = GetComponent<Animator>();
    }

    // Update is called once per frame

    void Update()
    {

        if (PlayerFinal.transform.position.z <= CheckpointFinal.transform.position.z)
        {
            anim.SetBool("isArrivedFinal", true);
            PolicemanFinal.GetComponent<Animator>().Play("Idle_Texting");
            Invoke("CallAmbulance", 1.0f);
        } 
    }

    void CallAmbulance()
    {
        if (CarFinal.transform.position.x <= Reference1Final.transform.position.x && ok == 0)
        {
            //Go right 
            transform.position -= new Vector3(-Speed * Time.deltaTime, 0, 0);
        }

        else
        {
            ok = 1;
            CarFinal.transform.rotation = Quaternion.Euler(0, 0, 0);
            if (CarFinal.transform.position.z <= Reference2Final.transform.position.z && ok == 0)
            {
                //Go right 
                transform.position -= new Vector3(0, 0, -Speed * Time.deltaTime);
            }
        }
    }
}
