package com.evangelidis.catsfacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangelidis.catsfacts.R
import com.evangelidis.catsfacts.model.DataX
import kotlinx.android.synthetic.main.row_item_layout.view.*
import okhttp3.internal.Util
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class CatfactsListAdapter(var catfacts : ArrayList<DataX>): RecyclerView.Adapter<CatfactsListAdapter.CatfactsViewHolder>() {

    fun updateCatfacts(newCatfacts: ArrayList<DataX>){
        catfacts.clear()
        catfacts.addAll(newCatfacts)
        notifyDataSetChanged()
    }

    private fun <T> Iterable<T>.shuffle(): List<T> {
        val list = this.toMutableList().apply {  }
        return list.shuffle()
    }

    fun shuffleCatfacts(){
        val x = catfacts.shuffle()
        catfacts.clear()
        catfacts.addAll(x)
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