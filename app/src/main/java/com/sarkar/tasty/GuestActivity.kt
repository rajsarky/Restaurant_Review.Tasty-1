package com.sarkar.tasty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sarkar.tasty.databinding.ActivityGuestBinding


class GuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.fragment_layout)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }
}