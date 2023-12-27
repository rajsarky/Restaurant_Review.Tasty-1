package com.sarkar.tasty.fragments

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sarkar.tasty.R
import com.sarkar.tasty.databinding.FragmentAddBinding
import com.sarkar.tasty.post.PostActivity
import com.sarkar.tasty.post.ReelsActivity

class AddFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener{
            activity?.startActivity(Intent(requireContext(), PostActivity::class.java))
            activity?.finish()
        }
        binding.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(), ReelsActivity::class.java))
        }

        return binding.root
    }

    companion object {

    }
}