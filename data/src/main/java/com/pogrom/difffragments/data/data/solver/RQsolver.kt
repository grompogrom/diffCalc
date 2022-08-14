package com.pogrom.difffragments.data.data.solver

import android.util.Log
import com.pogrom.difffragments.data.data.tool.EquationParser
import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.BoundaryConstraints
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.Coefs
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.PlotParams
import java.lang.Exception
import kotlin.properties.Delegates

class RQsolver: NumSolver() {
    lateinit var equation: Coefs
    lateinit var boundaryConditions: BoundaryConstraints
    lateinit var plotSettings: PlotParams
    private val yArr = mutableListOf<Float>()
    private val zArr = mutableListOf<Float>()
    private val xArr = mutableListOf<Float>()

    lateinit var coefA: EquationParser
    lateinit var coefB: EquationParser
    lateinit var coefC: EquationParser
    lateinit var coefD: EquationParser

    var length by Delegates.notNull<Double>()
    var h by Delegates.notNull<Float>()


    private fun uFoo(x: Float, y: Float, z: Float): Float{
        return ((coefD.returnCalculate(x) - coefB.returnCalculate(x) * z - coefC.returnCalculate(x) * y)/
                coefA.returnCalculate(x)).toFloat()
    }

    private fun Vfoo(z: Float) = z

    private fun setup(){
        xArr.clear()
        yArr.clear()
        zArr.clear()
        length = plotSettings.xMax - plotSettings.xMin
        h = (length / plotSettings.accuracy).toFloat()
        coefA = EquationParser(equation.a)
        coefB = EquationParser(equation.b)
        coefC = EquationParser(equation.c)
        coefD = EquationParser(equation.fx)
        xArr.add(boundaryConditions.x.toFloat())
        yArr.add(boundaryConditions.y.toFloat())
        zArr.add(boundaryConditions.ydx.toFloat())
    }

    private fun getSolve(): Pair<MutableList<Float>, MutableList<Float>> {
        return xArr to yArr
    }

    private fun makeSolve(){
        setup()
        var k0: Float
        var k1: Float
        var k2: Float
        var k3: Float
        var q0: Float
        var q1: Float
        var q2: Float
        var q3: Float

        for (i in 0 until plotSettings.accuracy){
            k0 = Vfoo(zArr[i])
            q0 = uFoo(xArr[i], yArr[i], zArr[i])
            k1 = Vfoo(zArr[i] + q0 * h / 2)
            q1 = uFoo(
                x=xArr[i] + h / 2,
                y=yArr[i] + k0 * h / 2,
                z=zArr[i] + q0 * h/2
            )
            k2 = Vfoo(zArr[i] + q1 * h / 2)
            q2 = uFoo(
                x=xArr[i] + h / 2,
                y=yArr[i] + k1 * h / 2,
                z=zArr[i] + q1 * h/2
            )
            k3 = Vfoo(zArr[i] + q2 * h / 2)
            q3 = uFoo(
                x=xArr[i] + h / 2,
                y=yArr[i] + k2 * h / 2,
                z=zArr[i] + q2 * h/2
            )

            xArr.add(boundaryConditions.x.toFloat() + h * (i + 1))
            zArr.add(zArr[i] + h/6 * (q0 + 2 * q1 + 2 * q2 + q3))
            yArr.add(yArr[i] + h/6 * (k0 + 2 * k1 + 2 * k2 + k3))
        }
    }


    override fun validate(equationData: EquationData): Boolean {
        Log.e("solver", equationData.toString())
        return try {
            equation = equationData.coefs as Coefs
            boundaryConditions = equationData.constraints as BoundaryConstraints
            plotSettings = equationData.plot as PlotParams
            Log.e("solver", equation.toString())
            Log.e("solver", boundaryConditions.toString())
            Log.e("solver", plotSettings.toString())
            true
        } catch (e: Exception){
            false
        }

    }

    override fun solve(): Pair<MutableList<Float>, MutableList<Float>> {
        makeSolve()
        Log.e("solver", xArr.toString())
        Log.e("solver", yArr.toString())
        return getSolve()

    }


}