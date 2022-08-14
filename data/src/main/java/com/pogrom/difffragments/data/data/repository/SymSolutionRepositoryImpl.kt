package com.pogrom.difffragments.data.data.repository

import com.pogrom.difffragments.data.data.solver.SymSolver
import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.repository.SymSolutionRepository

class SymSolutionRepositoryImpl(private val symSolver: SymSolver): SymSolutionRepository {

    override fun validate(equationData: EquationData): Boolean {
        return symSolver.validate(equationData)

    }

    override fun getSolution(): String {
        return symSolver.solve()
    }
}