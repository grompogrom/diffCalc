package com.pogrom.difffragments.domain.model.linearSecondOrderEquation

import com.pogrom.difffragments.domain.model.fields.CoefsArgField
import com.pogrom.difffragments.domain.model.fields.CoefsFeld

data class CoefsArgs(
    val a: String,
    val b: String,
    val c: String,
    val fx: String
) : CoefsArgField {
    override fun toCoefs(): CoefsFeld {
        return Coefs(
            a = a,
            b = b,
            c = c,
            fx = fx
        )
    }
}