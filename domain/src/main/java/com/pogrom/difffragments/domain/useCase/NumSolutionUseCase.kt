package com.pogrom.difffragments.domain.useCase

import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.model.fields.CoefsArgField
import com.pogrom.difffragments.domain.model.fields.ConstraintsArgsField
import com.pogrom.difffragments.domain.model.fields.PlotParamsArgField
import com.pogrom.difffragments.domain.repository.NumSolutionRepository
import java.lang.NumberFormatException
import java.lang.instrument.IllegalClassFormatException

class NumSolutionUseCase(private val solutionRepository: NumSolutionRepository) {

    fun execute(): Pair<MutableList<Float>, MutableList<Float>>{
//        if (!solutionRepository.validate(equationData = EquationData())){
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