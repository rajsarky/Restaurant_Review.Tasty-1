package com.sarkar.tasty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sarkar.restaurantreview.Models.User
import com.sarkar.tasty.Models.Post
import com.sarkar.tasty.R
import com.sarkar.tasty.databinding.PostRvBinding
import com.sarkar.tasty.utils.USER_NODE

class PostAdapter(var context: Context, var postList: ArrayList<Post>):
    RecyclerView.Adapter<PostAdapter.MyHolder>(){
    inner class MyHolder( var binding: PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding = PostRvBinding.inflate(LayoutInflater.from(context), parent, false)

        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Firebase.firestore.collection(USER_NODE).document(postList.get(position).uid).get().addOnSuccessListener {
            var user = it.toObject<User>()
            Glide.with(context).load(user!!.image).placeholder(R.drawable.user)
            holder.binding.name.text = user.name
        }
        Glide.with(context).load(postList.get(position).postUrl)
    }
}