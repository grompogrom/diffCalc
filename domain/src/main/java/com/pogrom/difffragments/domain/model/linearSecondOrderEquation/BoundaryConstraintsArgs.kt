package com.pogrom.difffragments.domain.model.linearSecondOrderEquation

import com.pogrom.difffragments.domain.model.fields.ConstraintsArgsField
import com.pogrom.difffragments.domain.model.fields.ConstraintsFeld

data class BoundaryConstraintsArgs(
    val x: String,
    val y: String,
    val ydx: String,
): ConstraintsArgsField {
    override fun toConstraints(): ConstraintsFeld {
        return BoundaryConstraints(
            x = x.toDouble(),
            y = y.toDouble(),
            ydx = ydx.toDouble()
        )
    }
}