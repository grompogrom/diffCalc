package com.pogrom.difffragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.androidplot.xy.CatmullRomInterpolator
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot
import com.pogrom.difffragments.R
import com.pogrom.difffragments.data.data.solver.RQsolver
import com.pogrom.difffragments.data.data.solver.SymbolicalSolver
import com.pogrom.difffragments.databinding.FragmentCalculationsBinding
import com.pogrom.difffragments.domain.model.EquationData
import com.pogrom.difffragments.domain.model.linearSecondOrderEquation.*
import com.pogrom.difffragments.models.CalculationFragmentViewModel
import com.pogrom.difffragments.tools.factory


class CalculationsFragment : Fragment(R.layout.fragment_calculations){

    private lateinit var binding: FragmentCalculationsBinding
    private val viewModel: CalculationFragmentViewModel by viewModels { factory(RQsolver(), SymbolicalSolver()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculationsBinding.inflate(inflater, container, false)

        val seriesFormat = LineAndPointFormatter(this.context, R.xml.line_point_formatter_with_labels)
        seriesFormat.interpolationParams = CatmullRomInterpolator.Params(10,
            CatmullRomInterpolator.Type.Centripetal)
        val plot: XYPlot = binding.plot

        binding.buttonCalculate.setOnClickListener {
            with(binding) {
                val coefs = CoefsArgs(
                        editTextCefA.text.toString(),
                        editTextCoefB.text.toString(),
                        editTextCoefC.text.toString(),
                        editTextCoefD.text.toString()
                    )
                val constraints = BoundaryConstraintsArgs(
                        editTextMinX.text.toString(),
                        editTextY.text.toString(),
                        editTextYdx.text.toString()
                    )
                val plotParams = PlotParamsArgs(
                        editTextMinX.text.toString(),
                        editTextMaxX.text.toString(),
                        seekBar.progress
                    )
                plot.visibility = View.GONE
                progressBar.visibility = View.VISIBLE

                viewModel.startCalculations(binding.root.context, coefs, constraints, plotParams)
            }
        }
        val plotObserver = Observer<Pair<MutableList<Float>, MutableList<Float>>> { pair ->
            Log.e("view", "X count ${ pair.first.count() }")
            Log.e("view", "Y count ${ pair.second.count() }")

            val series1  = SimpleXYSeries(
                pair.first, pair.second, "Series1"
            )
            plot.clear()
            plot.addSeries(series1, seriesFormat)
            plot.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            plot.redraw()
        }

        val numSolutionObserver = Observer<String>{
            binding.textSymSolution.text = it
        }

        viewModel.coords.observe(viewLifecycleOwner, plotObserver)
        viewModel.symSolutionText.observe(viewLifecycleOwner, numSolutionObserver)
        return binding?.root
    }


}