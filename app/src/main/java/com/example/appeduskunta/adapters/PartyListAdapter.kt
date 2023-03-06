package com.example.appeduskunta.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appeduskunta.databinding.PartyListBinding
import com.example.appeduskunta.fragments.PartyListFragmentDirections
import com.example.appeduskunta.models.Parliament



//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class PartyListAdapter : RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {

//    Here we collect data without duplicates
    private var partyName = mutableSetOf<String>()

    inner class ViewHolder(private val binding: PartyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(partyName: String) {
            binding.partyList.text = partyName
            binding.executePendingBindings()
            binding.root.setOnClickListener { view ->
                view.findNavController().navigate(
                    PartyListFragmentDirections.actionPartyListFragment2ToMemberInfoFragment(partyName)
                )
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PartyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPartyName = partyName.elementAt(position)
        holder.bind(currentPartyName)
    }

    override fun getItemCount(): Int {
        return partyName.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(parliament: List<Parliament>) {
        parliament.forEach {
            partyName.add(it.party)
        }
        notifyDataSetChanged()
    }
}


