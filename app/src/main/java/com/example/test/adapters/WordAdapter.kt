package com.example.test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.models.Definition
import com.example.test.models.MeaningResponse
import kotlinx.android.synthetic.main.item_meaning.view.*

class WordAdapter(private val meaningList:List<Definition>):RecyclerView.Adapter<WordAdapter.MeaningViewholder>() {
    inner class MeaningViewholder(itemView:View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewholder {
        return MeaningViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_meaning,parent,false))
    }

    override fun onBindViewHolder(holder: MeaningViewholder, position: Int) {
        val meaning = meaningList[position]
        holder.apply {
            itemView.meaningType.text = meaning.type
            itemView.defination.text = meaning.definition
        }
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }
}