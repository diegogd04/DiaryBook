package edu.iesam.diarybook.features.event.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.features.event.domain.Event

class EventViewHolder(
    private val view: View,
    private val onItemClickListener: (Event) -> Unit
) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Event) {
        val color =
            ContextCompat.getColor(view.context, R.color.md_theme_secondaryFixedDim_highContrast)
        binding = ViewActivityItemBinding.bind(view)

        binding.apply {
            this.color.visibility = View.GONE
            title.apply {
                text = item.title
            }.setTextColor(color)
            description.text = item.description
            cardViewItem.apply {
                strokeColor = color
                setOnClickListener {
                    onItemClickListener(item)
                }
                if (item.old) {
                    icFinalized.apply {
                        setColorFilter(color)
                        visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}