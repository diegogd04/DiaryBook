package edu.iesam.diarybook.features.event.presentation.eventDetail

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class EventDetailFragment : Fragment() {

    private val args: EventDetailFragmentArgs by navArgs()
    val event = args.event
}