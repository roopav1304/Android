package com.example.myprofile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {

      var photo : ImageView? = null
      var eduButton : Button? = null
      var workButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homescreen)

       photo = findViewById(R.id.profilePhoto)
        eduButton = findViewById(R.id.Education)
        workButton = findViewById(R.id.Work)

       photo?.setOnClickListener {
           var clickIntent = Intent(this@MainActivity, ProfilePhoto::class.java)
           startActivity(clickIntent)
       }
        eduButton?.setOnClickListener {
            var clickIntent = Intent(this@MainActivity, Education::class.java)
            startActivity(clickIntent)
        }
        workButton?.setOnClickListener {
            var clickIntent = Intent(this@MainActivity, Work_Experience::class.java)
            startActivity(clickIntent)
        }
    }
}
