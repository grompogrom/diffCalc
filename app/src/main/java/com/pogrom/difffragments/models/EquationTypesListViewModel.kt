package com.pogrom.difffragments.models

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.pogrom.difffragments.EquationType
import com.pogrom.difffragments.fragments.EquationTypesListFragmentDirections

class EquationTypesListViewModel(
    val navController: NavController
): ViewModel() {

    fun navigate(){
        val action = EquationTypesListFragmentDirections
            .actionEquationTypesListFragmentToCalculationsFragment()
        navController.navigate(action)
    }

    fun loadEquationTypeList(): List<EquationType> {
        return listOf(
            EquationType("Linear 2-order", "Ay'' + By' + Cy = D"),
            EquationType("test1", "y = sz + 4"),
            EquationType("test2", "y = sz + 5"),
            EquationType("test3", "y = sz + 6"),
        )
    }
}