package com.sarkar.tasty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sarkar.tasty.Models.Post
import com.sarkar.tasty.adapters.PostAdapter
import com.sarkar.tasty.databinding.FragmentDiscoveryBinding
import com.sarkar.tasty.utils.POST


class DiscoveryFragment : Fragment() {
    private lateinit var binding: FragmentDiscoveryBinding
    private var postList = ArrayList<Post>()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        adapter = PostAdapter(requireContext(), postList)
        binding.postRv.layoutManager = LinearLayoutManager(requireContext())
        binding.postRv.adapter = adapter

        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar2)


        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var tempList = ArrayList<Post>()
            postList.clear()
            for (i in it.documents){
                var post:Post = i.toObject<Post>()!!
                tempList.add(post)
            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {

    }
}