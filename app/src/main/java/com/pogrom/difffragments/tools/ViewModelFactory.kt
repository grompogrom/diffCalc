package com.pogrom.difffragments.tools

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.pogrom.difffragments.data.data.solver.NumSolver
import com.pogrom.difffragments.data.data.solver.SymSolver
import com.pogrom.difffragments.models.CalculationFragmentViewModel
import com.pogrom.difffragments.models.EquationTypesListViewModel

class ViewModelFactory(
    private val navController: NavController,
    private val numSolver: NumSolver? = null,
    private val symSolver: SymSolver? = null
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            EquationTypesListViewModel::class.java ->
                EquationTypesListViewModel(navController)
            CalculationFragmentViewModel::class.java ->
                numSolver?.let { symSolver?.let { it1 -> CalculationFragmentViewModel(it, it1) } }

            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }

}

fun Fragment.factory(numSolver: NumSolver? = null, symSolver: SymSolver? = null) =
    ViewModelFactory(
        navController = findNavController(),
        numSolver = numSolver,
        symSolver = symSolver
    )
