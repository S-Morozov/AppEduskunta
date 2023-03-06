package com.example.appeduskunta.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appeduskunta.databinding.RowItemsBinding
import com.example.appeduskunta.fragments.MemberInfoFragmentDirections
import com.example.appeduskunta.models.Parliament
import com.squareup.picasso.Picasso


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    //Base url link of the pictures of the parliament members
    private val baseUrl = "https://avoindata.eduskunta.fi/"

    private var parliamentMemberList = emptyList<Parliament>()

    inner class ViewHolder(private val binding: RowItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Parliament) {
            //loading image to the ImageView
            val imageUrl: String = baseUrl + item.picture
            Picasso.get().load(imageUrl).into(binding.memberImage)

            binding.firstName.text = item.first
            binding.lastName.text = item.last
            binding.region.text = item.constituency
            binding.partyName.text = item.party

            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                val personId = parliamentMemberList[position].personNumber
                val action = MemberInfoFragmentDirections.actionMemberInfoFragmentToMemberDetailsFragment(personId)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(parliamentMemberList[position])
    }

    override fun getItemCount(): Int {
        return parliamentMemberList.size
    }

    //parliamentMemberList is a list of ParliamentMember
//    partyName holds the value of the party name passed from previous fragment

    @SuppressLint("NotifyDataSetChanged")
    fun setData(parliamentMemberList: List<Parliament>, partyName: String) {
        //Filtering list by party name
        this.parliamentMemberList = parliamentMemberList.filter { it.party == partyName }
        notifyDataSetChanged()
    }
}


