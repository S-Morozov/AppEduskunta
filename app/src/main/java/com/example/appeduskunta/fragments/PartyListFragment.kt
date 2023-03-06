package com.example.appeduskunta.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appeduskunta.R
import com.example.appeduskunta.adapters.PartyListAdapter
import com.example.appeduskunta.databinding.FragmentPartyListBinding
import com.example.appeduskunta.viewmodels.ParliamentMemberViewModel


//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658

class PartyListFragment : Fragment() {

    private lateinit var binding: FragmentPartyListBinding

    private lateinit var mParliamentMemberViewModel: ParliamentMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_party_list, container, false)

        binding.partyListRecyclerView.setHasFixedSize(true)
        //RecyclerView adapter initialization
        val partyListAdapter = PartyListAdapter()
        binding.partyListRecyclerView.adapter = partyListAdapter
        binding.partyListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Initializing ViewMode class
        mParliamentMemberViewModel = ViewModelProvider(this)[ParliamentMemberViewModel::class.java]

        //Observing liveData of the ParliamentMemberViewModel class
        mParliamentMemberViewModel.parliamentMemberList.observe(viewLifecycleOwner) { response ->
            partyListAdapter.setData(response)
        }
        return binding.root
    }

}