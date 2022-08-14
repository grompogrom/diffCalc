package com.pogrom.difffragments.data

import com.pogrom.difffragments.data.data.repository.NumSolutionRepositoryImpl
import com.pogrom.difffragments.data.data.solver.RQsolver
import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.BoundaryConstraints
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.Coefs
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.PlotParams
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val equationData = EquationData(
        Coefs("1", "2", "1", "0"),
        BoundaryConstraints(0.0,1.0,0.0),
        PlotParams(0.0,5.0,20)
        )

        val numSolutionRepository = NumSolutionRepositoryImpl(RQsolver())

        assertEquals(true, numSolutionRepository.validate(equationData))
        println(numSolutionRepository.getSolution())
    }
}