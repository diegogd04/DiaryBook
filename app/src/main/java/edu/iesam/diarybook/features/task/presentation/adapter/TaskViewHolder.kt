package edu.iesam.diarybook.features.task.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.features.task.domain.Task

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Task) {
        binding = ViewActivityItemBinding.bind(view)

        binding.text.text = item.title
    }
}