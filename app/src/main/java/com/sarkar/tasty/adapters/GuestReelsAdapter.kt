//package com.sarkar.tasty.adapters
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.sarkar.tasty.Models.Reel
//import com.sarkar.tasty.databinding.ReelDgBinding
//
//class GuestReelsAdapter(var context: Context, var reelList: ArrayList<Reel>): RecyclerView.Adapter<GuestReelsAdapter.ViewHolder>() {
//
//    inner class ViewHolder(var binding: ReelDgBinding): RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        var binding = ReelDgBinding.inflate(LayoutInflater.from(context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return reelList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//}