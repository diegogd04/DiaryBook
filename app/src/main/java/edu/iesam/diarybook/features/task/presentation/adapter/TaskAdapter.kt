package edu.iesam.diarybook.features.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.diarybook.R
import edu.iesam.diarybook.features.task.domain.Task

class TaskAdapter : ListAdapter<Task, TaskViewHolder>(TaskDiffUtil()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_activity_item, viewGroup, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }
}