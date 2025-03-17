package edu.iesam.diarybook.features.event.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.features.event.domain.Event

class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Event) {
        binding = ViewActivityItemBinding.bind(view)

        binding.text.text = item.title
    }
}