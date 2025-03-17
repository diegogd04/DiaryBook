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
        var color = 0

        binding = ViewActivityItemBinding.bind(view)

        binding.text.text = item.title

        color = if (item is Event) {
            ContextCompat.getColor(view.context, R.color.blue)
        } else {
            ContextCompat.getColor(view.context, R.color.red)
        }
        binding.color.setBackgroundColor(color)
    }
}