package com.example.parsestagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.parse.Parse
import com.parse.ParseUser
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ParseUser.logOut()
        if(ParseUser.getCurrentUser() != null)
        {
            gotoMainActivity()
        }

        findViewById<Button>(R.id.et_Login_button).setOnClickListener {
            val username = findViewById<TextView>(R.id.et_username).text.toString()
            val password = findViewById<TextView>(R.id.et_password).text.toString()
            loginUser(username,password)
        }
        findViewById<Button>(R.id.SignUpButton).setOnClickListener {
            val username = findViewById<TextView>(R.id.et_username).text.toString()
            val password = findViewById<TextView>(R.id.et_password).text.toString()
            signUpUser(username,password)
        }
    }

    private fun signUpUser(username: String, password: String)
    {
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground() { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
            } else {
                Toast.makeText(this, "Failed to Logged in to instagram", Toast.LENGTH_SHORT).show()
              e.printStackTrace()
            }
        }
    }

    private fun loginUser(username: String, password:String)
    {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully login as the user")
                gotoMainActivity()
                // Hooray!  The user is logged in.
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Failed to Logged in to instagram", Toast.LENGTH_SHORT).show()
            }})
        )

    }

    private fun gotoMainActivity() {
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    companion object {
        const val TAG = "LoginActivity"
    }

}