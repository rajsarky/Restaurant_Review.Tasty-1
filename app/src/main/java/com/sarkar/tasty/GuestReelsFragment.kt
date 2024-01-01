package com.sarkar.tasty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.sarkar.tasty.Models.Reel
import com.sarkar.tasty.adapters.ReelAdapter
import com.sarkar.tasty.databinding.FragmentGuestReelsBinding
import com.sarkar.tasty.databinding.FragmentReelBinding
import com.sarkar.tasty.utils.REEL


class GuestReelsFragment : Fragment() {
    private lateinit var binding: FragmentGuestReelsBinding
    lateinit var adapter: ReelAdapter
    var reelList=ArrayList<Reel>()

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
        binding =  FragmentGuestReelsBinding.inflate(inflater, container, false)
        adapter=ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var tempList=ArrayList<Reel>()
            reelList.clear()

            for (i in it.documents){
                var reel = i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {

    }
}