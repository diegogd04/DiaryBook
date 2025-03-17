package edu.iesam.diarybook.features.event.data.remote

import edu.iesam.diarybook.features.event.domain.Event

fun EventDbModel.toModel(): Event {
    return Event(
        this.id,
        this.title,
        this.description,
        this.time,
        this.date,
        this.duration,
        this.old
    )
}