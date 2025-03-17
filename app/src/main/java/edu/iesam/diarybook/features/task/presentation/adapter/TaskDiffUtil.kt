package edu.iesam.diarybook.features.task.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.diarybook.features.task.domain.Task

class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}