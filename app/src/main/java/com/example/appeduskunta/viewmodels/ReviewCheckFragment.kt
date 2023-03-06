package com.example.appeduskunta.viewmodels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appeduskunta.R
import com.example.appeduskunta.adapters.ReviewCheckAdapter
import com.example.appeduskunta.databinding.FragmentReviewCheckBinding


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


class ReviewCheckFragment : Fragment() {
    private lateinit var binding: FragmentReviewCheckBinding
    private lateinit var mReviewViewModel: ReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_review_check, container, false)

        //Initializing viewModel
        mReviewViewModel = ViewModelProvider(this)[ReviewViewModel::class.java]

        //Stores the id of the personNumber passed by previous fragment
        val personId = ReviewCheckFragmentArgs.fromBundle(requireArguments()).itemId

        binding.checkReviewRecyclerView.setHasFixedSize(true)
        //RecyclerView adapter initialization
        val reviewAdapter = ReviewCheckAdapter()
        binding.checkReviewRecyclerView.adapter = reviewAdapter
        binding.checkReviewRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Observing liveData of the ReviewViewModel class
        mReviewViewModel.reviewList.observe(viewLifecycleOwner) {
            reviewAdapter.setData(it, personId)
        }

        return binding.root
    }

}