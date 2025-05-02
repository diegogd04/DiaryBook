package edu.iesam.diarybook.features.event.data.remote

import edu.iesam.diarybook.features.event.domain.Event

fun EventDbModel.toModel(): Event {
    return Event(
        this.id,
        this.title,
        this.description,
        this.time,
        this.userId,
        this.date,
        this.hour,
        this.duration,
        this.old
    )
}

fun Event.toEventDbModel(): EventDbModel {
    return EventDbModel(
        this.id,
        this.title,
        this.description,
        this.date,
        this.hour,
        this.duration,
        this.old,
        this.time,
        this.userId
    )
}