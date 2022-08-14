package com.pogrom.difffragments.domain.repository

import com.pogrom.difffragments.domain.model.EquationData

interface SymSolutionRepository {

    fun validate(equationData: EquationData): Boolean

    fun getSolution(): String

}