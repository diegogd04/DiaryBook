package edu.iesam.diarybook.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.task.domain.Task

class ActivityViewHolder(
    private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Activity) {
        binding = ViewActivityItemBinding.bind(view)
        var color = 0
        if (item is Event) {
            color = ContextCompat.getColor(
                view.context,
                R.color.md_theme_secondaryFixedDim_highContrast
            )
            binding.cardViewItem.setOnClickListener {
            }
        } else if (item is Task){
            color = ContextCompat.getColor(view.context, R.color.md_theme_tertiary)
            binding.cardViewItem.setOnClickListener {
            }
        }

        binding.apply {
            text.apply {
                text = item.title
            }.setTextColor(color)
            cardViewItem.strokeColor = color
            this.color.setBackgroundColor(color)
        }
    }
}