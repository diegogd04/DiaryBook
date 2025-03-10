package edu.iesam.diarybook.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.diarybook.domain.Activity

class ActivityDiffUtil : DiffUtil.ItemCallback<Activity>() {

    override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
        return oldItem == newItem
    }
}