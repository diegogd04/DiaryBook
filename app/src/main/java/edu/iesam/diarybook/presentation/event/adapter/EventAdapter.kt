package edu.iesam.diarybook.presentation.event.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.diarybook.R
import edu.iesam.diarybook.domain.event.Event

class EventAdapter : ListAdapter<Event, EventViewHolder>(EventDiffUtil()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_activity_item, viewGroup, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: EventViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }
}