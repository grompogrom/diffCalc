package com.pogrom.difffragments.data.data.repository

import com.pogrom.difffragments.data.data.solver.NumSolver
import com.pogrom.difffragments.data.data.solver.RQsolver
import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.repository.NumSolutionRepository

class NumSolutionRepositoryImpl(private val numSolver: NumSolver): NumSolutionRepository {

    override fun validate(equationData: EquationData): Boolean {
        numSolver.validate(equationData)
        return true
    }

    override fun getSolution(): Pair<MutableList<Float>, MutableList<Float>> {
        return numSolver.solve()
    }

}