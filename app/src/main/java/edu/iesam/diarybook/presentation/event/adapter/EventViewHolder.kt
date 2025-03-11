package edu.iesam.diarybook.presentation.event.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.domain.event.Event

class EventViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Event) {
        binding = ViewActivityItemBinding.bind(view)

        binding.text.text = item.title
    }
}