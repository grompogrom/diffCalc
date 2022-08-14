package com.pogrom.difffragments.domain.model.linearSecondOrderEquation

import com.pogrom.difffragments.domain.model.fields.PlotParamsArgField
import com.pogrom.difffragments.domain.model.fields.PlotParamsField

data class PlotParamsArgs(
    val xMin: String,
    val xMax: String,
    val accuracy: Int
): PlotParamsArgField {
    override fun toPlotParams(): PlotParamsField {
        return PlotParams(
            xMin = xMin.toDouble(),
            xMax = xMax.toDouble(),
            accuracy = accuracy
        )
    }
}