package com.evangelidis.catsfacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.catsfacts.R
import com.evangelidis.catsfacts.model.CatFact
import kotlinx.android.synthetic.main.row_item_layout.view.*

class CatFactsListAdapter(var catfacts : MutableList<CatFact>): RecyclerView.Adapter<CatFactsListAdapter.CatFactsViewHolder>() {

    fun updateCatFacts(newCatFacts: MutableList<CatFact>){
        catfacts.clear()
        catfacts.addAll(newCatFacts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatFactsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
    )

    override fun getItemCount() = catfacts.size

    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        holder.bind(catfacts[position])
    }

    class CatFactsViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val catFactContent = view.cat_fact

        fun bind(fact: CatFact){
            catFactContent.text = fact.fact
        }
    }
}