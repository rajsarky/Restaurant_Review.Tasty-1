package com.sarkar.tasty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            if(FirebaseAuth.getInstance().currentUser==null)
            startActivity(Intent(this, SignUpActivity::class.java))
            else
                startActivity(Intent(this, MemberUserHomeActivity::class.java))
            finish()
        }, 3000)

    }
}