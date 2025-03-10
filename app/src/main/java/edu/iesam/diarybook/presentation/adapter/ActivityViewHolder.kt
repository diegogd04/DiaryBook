package edu.iesam.diarybook.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.diarybook.databinding.ViewActivityItemBinding
import edu.iesam.diarybook.domain.Activity

class ActivityViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewActivityItemBinding

    fun bind(item: Activity) {
        binding = ViewActivityItemBinding.bind(view)

        binding.text.text = item.title
    }
}