package edu.iesam.diarybook.features.event.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.diarybook.features.event.domain.Event

class EventDiffUtil : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}