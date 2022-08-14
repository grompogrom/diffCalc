package com.pogrom.difffragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pogrom.difffragments.databinding.ElementSolutionTypeBinding
import com.pogrom.difffragments.databinding.FragmentCalculationsBinding

class EquationTypeAdapter(
    private val typeSet: List<EquationType>,
    private val clickListener: (EquationType) -> Unit
): RecyclerView.Adapter<EquationTypeAdapter.ViewHolder>() {


    class ViewHolder(
        private val binding: ElementSolutionTypeBinding,
        private val clickAtPosition: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EquationType){
            binding.root.setOnClickListener {
//                clickListener(typeSet[adapterPosition])
                clickAtPosition(adapterPosition)
            }
            with(binding){
                textEqType.text = item.name
                textPreview.text = item.preview
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ElementSolutionTypeBinding.inflate(view, parent, false)
        return ViewHolder(binding) { clickListener(typeSet[it]) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(typeSet[position])
    }

    override fun getItemCount(): Int {
        return typeSet.size
    }
}