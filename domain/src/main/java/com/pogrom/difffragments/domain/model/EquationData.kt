package com.pogrom.difffragments.domain.model

import com.pogrom.difffragments.domain.model.fields.CoefsFeld
import com.pogrom.difffragments.domain.model.fields.ConstraintsFeld
import com.pogrom.difffragments.domain.model.fields.PlotParamsField

data class EquationData(
    val coefs: CoefsFeld,
    val constraints: ConstraintsFeld,
    val plot: PlotParamsField
)
