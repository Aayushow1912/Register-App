package com.example.registerapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var database : DatabaseReference
class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val signupbtn=findViewById<Button>(R.id.btnSignUp)
      val etName=findViewById<TextInputEditText>(R.id.etName)
      val etMail=findViewById<TextInputEditText>(R.id.etMail)
      val userId=findViewById<TextInputEditText>(R.id.etUniqueId)
      val userpass=findViewById<TextInputEditText>(R.id.etPassword)

        signupbtn.setOnClickListener {
            val name=etName.text.toString()
            val mail=etMail.text.toString()
            val uniqueid=userId.text.toString()
            val password=userpass.text.toString()

            val user=Users(name,mail,password,uniqueid)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueid).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
                userId.text?.clear()
                userpass.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        val signInText = findViewById<TextView>(R.id.tVSignIN)
        signInText.setOnClickListener {
            val openSignInAcitivity= Intent(this, SignIn::class.java)
            startActivity(openSignInAcitivity)
        }
    }
}