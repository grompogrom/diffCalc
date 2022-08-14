package com.pogrom.difffragments.domain.model.linearSecondOrderEquation

import com.pogrom.difffragments.domain.model.fields.ConstraintsFeld

data class BoundaryConstraints(
    val x: Double,
    val y: Double,
    val ydx: Double,
) : ConstraintsFeld()