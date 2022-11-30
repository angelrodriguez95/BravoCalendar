package com.example.bravocalendar.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.bravocalendar.databinding.EventItemBinding
import com.example.bravocalendar.model.Event

class CardViewHolder(
    private val eventItemBinding: EventItemBinding
): RecyclerView.ViewHolder(eventItemBinding.root) {

    fun bindEvent(event: Event) {
        eventItemBinding.title.text = event.title
        eventItemBinding.time.text = "${event.hours}:${event.minutes}"
    }
}