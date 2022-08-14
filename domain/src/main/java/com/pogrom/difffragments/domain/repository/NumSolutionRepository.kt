package com.pogrom.difffragments.domain.repository

import com.pogrom.difffragments.domain.model.EquationData

interface NumSolutionRepository {

    fun validate(equationData: EquationData): Boolean

    fun getSolution(): Pair<MutableList<Float>, MutableList<Float>>

}