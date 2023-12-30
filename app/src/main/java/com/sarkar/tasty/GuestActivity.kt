package com.sarkar.tasty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sarkar.tasty.databinding.ActivityGuestBinding
import com.sarkar.tasty.databinding.ActivitySignUpBinding

class GuestActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityGuestBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)


    }
}