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
import com.example.appeduskunta.adapters.MemberAdapter
import com.example.appeduskunta.databinding.FragmentMemberInfoBinding
import com.example.appeduskunta.viewmodels.ParliamentMemberViewModel

//
//  Author: Semen Morozov
//  date: 6.03.2023
//  Student id:2217658


class MemberInfoFragment : Fragment() {

    private lateinit var binding: FragmentMemberInfoBinding
    private lateinit var mParliamentMemberViewModel: ParliamentMemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_member_info, container, false)

        binding.mainRecyclerView.setHasFixedSize(true)
        //RecyclerView adapter initialization
        val partyMemberAdapter = MemberAdapter()
        binding.mainRecyclerView.adapter = partyMemberAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Storing value of the party name from PartyList fragment
        val partyName = MemberInfoFragmentArgs.fromBundle(requireArguments()).itemName

        //Initializing ViewModel class
        mParliamentMemberViewModel = ViewModelProvider(this)[ParliamentMemberViewModel::class.java]

        //Observing liveData of the ParliamentMemberViewModel class
        mParliamentMemberViewModel.parliamentMemberList.observe(
            viewLifecycleOwner
        ) { parliamentMember ->
            partyMemberAdapter.setData(parliamentMember, partyName)
        }
        return binding.root
    }
}