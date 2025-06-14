package edu.iesam.diarybook.features.event.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.diarybook.R
import edu.iesam.diarybook.features.event.domain.Event

class EventAdapter(private val onItemClickListener: (Event) -> Unit) :
    ListAdapter<Event, EventViewHolder>(EventDiffUtil()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_activity_item, viewGroup, false)
        return EventViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: EventViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }
}