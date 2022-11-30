package com.example.bravocalendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bravocalendar.databinding.EventItemBinding
import com.example.bravocalendar.model.Event
import com.example.bravocalendar.viewholder.CardViewHolder

class CardAdapter(private val events: List<Event>): RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(from, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindEvent(events[position])
    }

    override fun getItemCount(): Int = events.size


}