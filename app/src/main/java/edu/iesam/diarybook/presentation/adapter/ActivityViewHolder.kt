package edu.iesam.diarybook.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.features.event.domain.Event

class ActivityViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Activity) {
        binding = ViewActivityItemBinding.bind(view)
        val color = if (item is Event) {
            ContextCompat.getColor(
                view.context,
                R.color.md_theme_secondaryFixedDim_highContrast
            )
        } else {
            ContextCompat.getColor(view.context, R.color.md_theme_tertiary)
        }

        binding.apply {
            title.apply {
                text = item.title
            }.setTextColor(color)
            description.text = item.description
            cardViewItem.strokeColor = color
            this.color.setBackgroundColor(color)
        }
    }
}