package edu.iesam.diarybook.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.diarybook.R
import edu.iesam.diarybook.domain.Activity

class ActivityAdapter : ListAdapter<Activity, ActivityViewHolder>(ActivityDiffUtil()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_activity_item, viewGroup, false)

        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: ActivityViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }
}