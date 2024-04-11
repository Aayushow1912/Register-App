package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY1="com.example.registerapp.SignIn.mail"
        const val KEY2="com.example.registerapp.SignIn.name"
        const val KEY3="com.example.registerapp.SignIn.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val SignInBtn=findViewById<Button>(R.id.btnSignIn)
        val username=findViewById<TextInputEditText>(R.id.etUsername)

        SignInBtn.setOnClickListener {

            val UniqueId=username.text.toString()
            if(UniqueId.isNotEmpty()){
                  readData(UniqueId)
            }
            else{
                Toast.makeText(this, "Please Enter the Username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(Uniqueid: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(Uniqueid).get().addOnSuccessListener {

            if (it.exists()){
                //welcome user in ur app with intent and also pass
                    val emailId=it.child("email").value
                    val Name=it.child("name").value
                    val UniqueID=it.child("uniqueId").value

                val intentWelcome=Intent(this, HomeActivity::class.java)
                intentWelcome.putExtra(KEY1,emailId.toString())
                intentWelcome.putExtra(KEY2,Name.toString())
                intentWelcome.putExtra(KEY3,UniqueID.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Failed Login", Toast.LENGTH_SHORT).show()
        }
    }
}