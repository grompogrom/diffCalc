package com.pogrom.difffragments.models

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogrom.difffragments.MainActivity
import com.pogrom.difffragments.data.data.repository.NumSolutionRepositoryImpl
import com.pogrom.difffragments.data.data.repository.SymSolutionRepositoryImpl
import com.pogrom.difffragments.data.data.solver.NumSolver
import com.pogrom.difffragments.data.data.solver.SymSolver
import com.pogrom.difffragments.domain.model.fields.CoefsArgField
import com.pogrom.difffragments.domain.model.fields.ConstraintsArgsField
import com.pogrom.difffragments.domain.model.fields.PlotParamsArgField
import com.pogrom.difffragments.domain.useCase.NumSolutionUseCase
import com.pogrom.difffragments.domain.useCase.SymSolutionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculationFragmentViewModel(
    numSolver: NumSolver,
    symSolver: SymSolver
): ViewModel() {
    private val numSolutionUseCase by lazy { NumSolutionUseCase(NumSolutionRepositoryImpl(numSolver)) }
    private val symSolutionUseCase by lazy { SymSolutionUseCase(SymSolutionRepositoryImpl(symSolver)) }
    val coords: MutableLiveData<Pair<MutableList<Float>, MutableList<Float>>> by lazy {
        MutableLiveData<Pair<MutableList<Float>, MutableList<Float>>>()
    }
    val symSolutionText: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }


    fun startCalculations(
        context: Context,
        coefsArg: CoefsArgField,
        constraintsArgs: ConstraintsArgsField,
        plotParamsArg: PlotParamsArgField
    ){
        val numValid = numSolutionUseCase.validate(coefsArg, constraintsArgs, plotParamsArg)
        val sumValid = symSolutionUseCase.validate(coefsArg, constraintsArgs, plotParamsArg)

        if(!numValid){
            errorToast(context)
            return
        }
        if (!sumValid){
            symSolutionText.postValue("Sorry we can't get symbolic solution.")
        }
        viewModelScope.launch(Dispatchers.Default){
            coords.postValue(numSolutionUseCase.execute())
            if (sumValid) {
                symSolutionText.postValue(symSolutionUseCase.execute())
            }
        }
    }

    fun errorToast(context: Context){
        val text = "Invalid data! Please check and fix :)"
        Toast.makeText(context, text, Toast.LENGTH_SHORT)

    }
}