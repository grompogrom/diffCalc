package com.pogrom.difffragments.domain.model.linearSecondOrderEquation

import com.pogrom.difffragments.domain.model.fields.PlotParamsField

data class PlotParams(
    val xMin: Double,
    val xMax: Double,
    val accuracy: Int
): PlotParamsField()