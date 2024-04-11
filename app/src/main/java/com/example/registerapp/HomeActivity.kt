package com.example.registerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val name=intent.getStringExtra(SignIn.KEY2)
        val mail=intent.getStringExtra(SignIn.KEY1)
        val userId=intent.getStringExtra(SignIn.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tWelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val UniqueText = findViewById<TextView>(R.id.tvUnique)

        welcomeText.text= "Welcome $name"
        mailText.text= "Your Mail Id is $mail"
        UniqueText.text="Your Unique ID is  $userId"

    }
}