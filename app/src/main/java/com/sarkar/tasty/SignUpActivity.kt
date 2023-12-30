package com.sarkar.tasty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sarkar.tasty.utils.uploadImage
import com.sarkar.restaurantreview.Models.User
import com.sarkar.tasty.databinding.ActivitySignUpBinding
import com.sarkar.tasty.utils.USER_NODE
import com.sarkar.tasty.utils.USER_PROFILE_FOLDER
import com.squareup.picasso.Picasso

class SignUpActivity : AppCompatActivity() {


    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    lateinit var user: User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->

        uri?.let{
            uploadImage(uri, USER_PROFILE_FOLDER){
                if (it==null){

                } else {
                    user.image=it
                    binding.profileImage.setImageURI(uri)
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val login = "<font color=#FF000000>Aready have an acount </font> <font color=#1E88E5>Login? </font>"
        binding.clickLogin.setText(Html.fromHtml(login))

        val guestLogin = "<font color=#FF000000>Do you want to login as a </font> <font color=#CA0F4E>Guest? </font>"
        binding.guestLogin.setText(Html.fromHtml(guestLogin))

        user = User()

        if (intent.hasExtra("MODE")){
            if (intent.getIntExtra("MODE", -1) == 1){
                binding.signUpBtn.text = "Update Profile"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                    user = it.toObject<User>()!!
                    if (!user.image.isNullOrEmpty()){
                        Picasso.get().load(user.image).into(binding.profileImage)
                    }

                    binding.name.editText?.setText(user.name)
                    binding.email.editText?.setText(user.email)
                    binding.password.editText?.setText(user.password)

                }
            }
        }

        binding.signUpBtn.setOnClickListener {

            if (intent.hasExtra("MODE")){
                if (intent.getIntExtra("MODE", -1) == 1){

                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            //Toast.makeText(this@SignUpActivity, "Sign Up is successful!", Toast.LENGTH_SHORT).show()
                            startActivity(
                                Intent(
                                    this@SignUpActivity,
                                    MemberUserHomeActivity::class.java
                                )
                            )
                            finish()
                        }

                }
            } else {

                if ((binding.name.editText?.text.toString().equals("")) or
                    (binding.email.editText?.text.toString().equals("")) or
                    (binding.password.editText?.text.toString().equals(""))
                ) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Please Fill all the required information",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.editText?.text.toString(),
                        binding.password.editText?.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.name = binding.name.editText?.text.toString()
                            user.email = binding.email.editText?.text.toString()
                            user.password = binding.email.editText?.text.toString()

                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    //Toast.makeText(this@SignUpActivity, "Sign Up is successful!", Toast.LENGTH_SHORT).show()
                                    startActivity(
                                        Intent(
                                            this@SignUpActivity,
                                            MemberUserHomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.profileImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.clickLogin.setOnClickListener{
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            //finish()
        }

        binding.guestLogin.setOnClickListener{
            startActivity(Intent(this@SignUpActivity, GuestActivity::class.java))
            finish()
        }

    }
}