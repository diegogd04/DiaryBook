package edu.iesam.diarybook.data.event.local

import edu.iesam.diarybook.domain.event.Event

fun EventEntity.toModel(): Event {
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

fun Event.toEntity(ms: Long): EventEntity {
    return EventEntity(
        this.id,
        this.title,
        this.description,
        this.time,
        this.date,
        this.duration,
        this.old,
        ms
    )
}