package com.evangelidis.catsfacts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.catsfacts.databinding.RowItemLayoutBinding
import com.evangelidis.catsfacts.model.CatFact

class CatFactsListAdapter(var catFacts: MutableList<CatFact>) :
    RecyclerView.Adapter<CatFactsListAdapter.CatFactsViewHolder>() {

    fun updateCatFacts(newCatFacts: MutableList<CatFact>) {
        catFacts.clear()
        catFacts.addAll(newCatFacts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatFactsViewHolder(
        RowItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    )

    override fun getItemCount() = catFacts.count()

    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        holder.bind(catFacts[position])
    }

    class CatFactsViewHolder(private val binding: RowItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: CatFact) {
            binding.catFact.text = fact.fact
        }
    }
}