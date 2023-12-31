package com.sarkar.tasty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import com.sarkar.tasty.R
import com.sarkar.tasty.databinding.SearchRvBinding
import com.sarkar.tasty.utils.FOLLOW

class SearchAdapter(var context: Context, var userList: ArrayList<com.sarkar.restaurantreview.Models.User>): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isFollow = false
        Glide.with(context).load(userList.get(position).image).placeholder(R.drawable.user).into(holder.binding.profileImg)
        holder.binding.nameSearch.text = userList.get(position).name
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {
            if (it.documents.size == 0){
                isFollow = false
            } else {
                holder.binding.follow.text = "Unfollow"
                isFollow= true
            }
        }

        holder.binding.follow.setOnClickListener{
            if (isFollow){
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).document(it.documents.get(0).id).delete()
                    holder.binding.follow.text = "Follow"
                    isFollow = false
                }
            } else{
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document().set(userList.get(position))
                holder.binding.follow.text = "Unfollow"
                isFollow = true
            }

        }
    }
}