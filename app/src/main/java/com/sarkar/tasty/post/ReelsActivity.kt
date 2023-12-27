package com.sarkar.tasty.post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sarkar.restaurantreview.Models.User
import com.sarkar.tasty.MemberUserHomeActivity
import com.sarkar.tasty.Models.Reel
import com.sarkar.tasty.databinding.ActivityReelsBinding
import com.sarkar.tasty.utils.REEL
import com.sarkar.tasty.utils.REEL_FOLDER
import com.sarkar.tasty.utils.USER_NODE
import com.sarkar.tasty.utils.uploadVideo

class ReelsActivity : AppCompatActivity() {

    private lateinit var videoUrl:String
    private lateinit var progressDialog:ProgressDialog

    val binding by lazy {
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->

        uri?.let{
            uploadVideo(uri, REEL_FOLDER, progressDialog){
                    url ->
                if (url!=null) {
                    videoUrl = url
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)

        binding.selectReel.setOnClickListener{
            launcher.launch("video/*")
        }

        binding.cancelButton.setOnClickListener{
            startActivity(Intent(this@ReelsActivity, MemberUserHomeActivity::class.java))
            finish()
        }
        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User = it.toObject<User>()!!
                val reel: Reel = Reel(videoUrl!!,binding.caption.editText?.text.toString(),user.image!!)

                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document().set(reel).addOnSuccessListener {
                        startActivity(Intent(this@ReelsActivity, MemberUserHomeActivity::class.java))
                        finish()
                    }
                }

            }

        }

    }

}