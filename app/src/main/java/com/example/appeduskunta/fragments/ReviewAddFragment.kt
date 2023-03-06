package com.example.appeduskunta.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.appeduskunta.R
import com.example.appeduskunta.databinding.FragmentReviewAddBinding
import com.example.appeduskunta.models.Review
import com.example.appeduskunta.viewmodels.ReviewViewModel

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class ReviewAddFragment : Fragment() {
    private lateinit var binding: FragmentReviewAddBinding
    private lateinit var mReviewViewModel: ReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_review_add, container, false)

        //Initializing ViewModel class
        mReviewViewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)

        //Stores the id of the personNumber passed by previous fragment
        val personId = ReviewAddFragmentArgs.fromBundle(requireArguments()).itemId

        //Inserting data to the database on clicking submit button
        binding.submit.setOnClickListener {
            val comment = binding.addComment.text.toString()
            val rating = binding.addRating.text.toString()
            val parsedRating = rating.toIntOrNull()
            //Checking if the user input is empty and input rating is convertible to integer
            //if it is then gives message on the screen to fill the empty field
            if (TextUtils.isEmpty(comment) || TextUtils.isEmpty(rating) || parsedRating == null) {
                Toast.makeText(requireContext(), "Please fill all the field properly",
                    Toast.LENGTH_LONG).show()
            } else {
                //Checking if member rating is lss than 1 or greater than 5
                // if it is then display will show error message
                if (rating.toInt() < 1 || rating.toInt() > 5) {
                    Toast.makeText(requireContext(), "Please put numbers between 1 to 5",
                        Toast.LENGTH_LONG).show()
                } else {
                    //Creating a review object
                    val review = Review(0, personId, comment, rating.toInt())
                    //Adding review to the ReviewDataBase
                    mReviewViewModel.addReview(review)
                    Toast.makeText(requireContext(), "Review added", Toast.LENGTH_LONG).show()

                    //Storing personNumber passed from the previous fragment
                    val id = ReviewAddFragmentArgs.fromBundle(requireArguments()).itemId
                    //Passing value of id to the next fragment
                    view?.findNavController()
                        ?.navigate(
                            ReviewAddFragmentDirections.actionReviewAddFragmentToReviewCheckFragment(
                                id
                            )
                        )
                }
            }
        }
        return binding.root
    }
}

