package com.evangelidis.catsfacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.catsfacts.R
import com.evangelidis.catsfacts.model.DataX
import kotlinx.android.synthetic.main.row_item_layout.view.*

class CatfactsListAdapter(var catfacts : MutableList<DataX>): RecyclerView.Adapter<CatfactsListAdapter.CatfactsViewHolder>() {

    fun updateCatfacts(newCatfacts: MutableList<DataX>){
        catfacts.clear()
        catfacts.addAll(newCatfacts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatfactsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
    )

    override fun getItemCount() = catfacts.size

    override fun onBindViewHolder(holder: CatfactsViewHolder, position: Int) {
        holder.bind(catfacts[position])
    }

    class CatfactsViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val catfactContent = view.cat_fact

        fun bind(fact: DataX){
            catfactContent.text = fact.fact
        }
    }
}