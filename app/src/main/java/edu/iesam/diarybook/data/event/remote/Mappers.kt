package edu.iesam.diarybook.data.event.remote

import edu.iesam.diarybook.domain.event.Event

fun EventDbModel.toModel(): Event {
    return Event(
        this.id,
        this.title,
        this.description,
        this.date,
        this.duration,
        this.old,
        this.time
    )
}