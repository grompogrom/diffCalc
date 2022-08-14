package com.pogrom.difffragments.domain.useCase

import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.model.fields.CoefsArgField
import com.pogrom.difffragments.domain.model.fields.ConstraintsArgsField
import com.pogrom.difffragments.domain.model.fields.PlotParamsArgField
import com.pogrom.difffragments.domain.repository.SymSolutionRepository
import java.lang.NumberFormatException
import java.lang.instrument.IllegalClassFormatException

class SymSolutionUseCase(private val solutionRepository: SymSolutionRepository) {

    fun execute(): String{
//        if (!solutionRepository.validate()){
//            throw IllegalClassFormatException()
//        }

        return solutionRepository.getSolution()
    }

    fun validate(
        coefsArgField: CoefsArgField,
        constraintsArgsField: ConstraintsArgsField,
        plotParamsArgField: PlotParamsArgField
    ): Boolean{

        return try {
            val equationData = EquationData(
                coefsArgField.toCoefs(),
                constraintsArgsField.toConstraints(),
                plotParamsArgField.toPlotParams())
            solutionRepository.validate(equationData)
        } catch (e: NumberFormatException){
            false
        }
    }
}