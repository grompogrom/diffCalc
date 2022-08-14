package com.pogrom.difffragments.domain.model.linearSecondOrderEquation

import com.pogrom.difffragments.domain.model.fields.CoefsFeld

data class Coefs (
    val a: String,
    val b: String,
    val c: String,
    val fx: String
): CoefsFeld()