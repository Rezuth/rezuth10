using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LevelWin : MonoBehaviour
{
 
    static Collider other;

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "fan")
        {
            SceneManager.LoadSceneAsync(SceneManager.GetActiveScene().buildIndex);
            //anim.SetBool("isDead7", true);
            //SceneManager.LoadScene(1);
        }
    }
}
