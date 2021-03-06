﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Doctor : MonoBehaviour
{
    float Speed = 2.0f;
    public GameObject hFan;
    public GameObject Car;
    public GameObject DoctorFinal;
    public GameObject Checkpoint;
    public GameObject PolicemanFinal;
    static Animator anim;
    int ok = 0;

    // Update is called once per frame


    void Start()
    {
        anim = GetComponent<Animator>();
        GameObject.Find("man-doctor (2)").transform.localScale = new Vector3(0, 0, 0);
        GameObject.Find("Vehicle_Ambulance (5)").transform.localScale = new Vector3(0, 0, 0);
    }

    void Update()
    {

        if (Car.transform.position.z > Checkpoint.transform.position.z)
        {
            GameObject.Find("man-doctor (2)").transform.localScale = new Vector3(1, 1, 1);

            if (DoctorFinal.transform.position.z >= hFan.transform.position.z+0.3f)
            {
                transform.position += new Vector3(0, 0, -Speed * Time.deltaTime);
                anim.SetBool("isRunningFinal", true);
            }

            else
            {
                anim.SetBool("isRunningFinal", false);
                DoctorFinal.GetComponent<Animator>().Play("Idle_Crouching");
                Invoke("TreatFan", 3.0f);
            }
        }

    }

   

    void TreatFan()
    {
        DoctorFinal.SetActive(false);
        hFan.SetActive(false);
        PolicemanFinal.transform.rotation = Quaternion.Euler(0, 179, 0);
        GameObject.Find("Vehicle_Ambulance (5)").transform.localScale = new Vector3(1, 1, 1);
        GameObject.Find("Vehicle_Ambulance (4)").transform.localScale = new Vector3(0, 0, 0);


    }






}



