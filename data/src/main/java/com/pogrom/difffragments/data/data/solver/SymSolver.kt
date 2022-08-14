package com.pogrom.difffragments.data.data.solver

import com.pogrom.difffragments.domain.model.EquationData

abstract class SymSolver() {

    abstract fun validate(equationData: EquationData): Boolean

    abstract fun solve(): String

}