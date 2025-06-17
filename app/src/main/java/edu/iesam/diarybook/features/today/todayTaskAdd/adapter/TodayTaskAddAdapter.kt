package edu.iesam.diarybook.features.today.todayTaskAdd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.diarybook.R
import edu.iesam.diarybook.features.task.domain.Task

class TodayTaskAddAdapter(private val onItemClickListener: (Task) -> Unit) :
    ListAdapter<Task, TodayTaskAddViewHolder>(TodayTaskAddDiffUtil()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodayTaskAddViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_activity_item, viewGroup, false)
        return TodayTaskAddViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: TodayTaskAddViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }
}