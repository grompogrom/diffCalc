package com.pogrom.difffragments.domain.model

data class InputEquationData(
    val coefs: Map<String, String>,
    val constraints: Map<String, String>
)
