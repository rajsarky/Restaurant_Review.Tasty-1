package com.sarkar.tasty.post

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sarkar.tasty.MemberUserHomeActivity
import com.sarkar.tasty.Models.Post

import com.sarkar.tasty.databinding.ActivityPostBinding
import com.sarkar.tasty.utils.POST
import com.sarkar.tasty.utils.POST_FOLDER
import com.sarkar.tasty.utils.USER_NODE
import com.sarkar.tasty.utils.USER_PROFILE_FOLDER
import com.sarkar.tasty.utils.uploadImage
import com.sarkar.restaurantreview.Models.User


class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl:String?=null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->

        uri?.let{
            uploadImage(uri, POST_FOLDER){
                url ->
                if (url!=null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl = url
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity, MemberUserHomeActivity::class.java))
            finish()
        }

        binding.selectImage.setOnClickListener{
            launcher.launch("image/*")
        }

        binding.cancelButton.setOnClickListener{
            startActivity(Intent(this@PostActivity, MemberUserHomeActivity::class.java))
            finish()
        }

        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document().get().addOnSuccessListener {
                var user = it.toObject<User>()!!
                val post: Post = Post(
                    postUrl = imageUrl!!,
                    caption = binding.caption.editText?.text.toString(),
                    uid = Firebase.auth.currentUser!!.uid,
                    time = System.currentTimeMillis().toString()
                )

                Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                        startActivity(Intent(this@PostActivity, MemberUserHomeActivity::class.java))
                        finish()
                    }
                }
            }

        }
    }
}