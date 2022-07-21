 package com.activity.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

 class MainActivity : AppCompatActivity() {

     private var count = 0
     private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDownload = findViewById<Button>(R.id.btnDownload)
        val btnCount = findViewById<Button>(R.id.btnCount)
        val tvCont = findViewById<TextView>(R.id.tvCount)
        message = findViewById(R.id.tvMessage)

        btnCount.setOnClickListener {
            tvCont.text = count++.toString()
        }

        btnDownload.setOnClickListener {
            //coroutineScope is an interface provide the scope of the coroutine
            //need to provide the context(Dispatchers.IO) it will run in the background thread
            //launch is the coroutine builder to create the coroutine
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }
     //suspend keyword for delayed of coroutine library
     //use suspend in function to change to another thread
     /*suspending function can be invoke from a coroutine block or from
     from an another suspending function only */
     private suspend fun downloadUserData() {
         for(i in 1..200000){
             Log.i("tag","Downloading User $i in ${Thread.currentThread().name}")

             //switch coroutine from one thread to another with withContext
             //switch to main context/thread Dispatcher.Main
             withContext(Dispatchers.Main){
                 message.text = "Downloading User $i"
             }

             //to delay the iteration by 100 ms
             delay(100)
         }
     }

 }