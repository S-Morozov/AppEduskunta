package com.example.appeduskunta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appeduskunta.databinding.RowReviewItemsBinding
import com.example.appeduskunta.models.Review



//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class ReviewCheckAdapter : RecyclerView.Adapter<ReviewCheckAdapter.ViewHolder>() {

    private var reviewList = emptyList<Review>()

    inner class ViewHolder(private val binding: RowReviewItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            binding.userComment.text = review.comment
            binding.userRating.text = review.rating.toString()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowReviewItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = reviewList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

//    *
//     *  reviewList holds the value of reviewList
//     * personId holds the value of personNumber passed from previous fragment
//     *
    fun setData(reviewList: List<Review>, personId: Int) {
        //Filtering review list through person id
        this.reviewList = reviewList.filter { it.personId == personId }
        notifyDataSetChanged()
    }
}

