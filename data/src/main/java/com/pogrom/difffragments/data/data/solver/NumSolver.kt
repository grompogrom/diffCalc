package com.pogrom.difffragments.data.data.solver

import com.pogrom.difffragments.domain.model.EquationData

abstract class NumSolver {

    abstract fun validate(equationData: EquationData): Boolean

    abstract fun solve(): Pair<MutableList<Float>, MutableList<Float>>
}