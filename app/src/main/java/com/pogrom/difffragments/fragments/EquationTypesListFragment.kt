package com.pogrom.difffragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.pogrom.difffragments.EquationTypeAdapter
import com.pogrom.difffragments.models.EquationTypesListViewModel
import com.pogrom.difffragments.databinding.FragmentEquationTypesListBinding
import com.pogrom.difffragments.tools.factory


class EquationTypesListFragment : Fragment(){

    private lateinit var listAdapter: EquationTypeAdapter
    private lateinit var binding: FragmentEquationTypesListBinding
    private val viewModel: EquationTypesListViewModel by viewModels { factory() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEquationTypesListBinding.inflate(inflater, container, false)

        listAdapter = EquationTypeAdapter(viewModel.loadEquationTypeList())
        { viewModel.navigate() }

        val recyclerView = binding.equationTypeRecyclerView
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        recyclerView.adapter = listAdapter

        return binding.root
    }
}