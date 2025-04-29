package edu.iesam.diarybook.features.event.data.local

import edu.iesam.diarybook.features.event.domain.Event

fun EventEntity.toModel(): Event {
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

fun Event.toEntity(ms: Long): EventEntity {
    return EventEntity(
        this.id,
        this.title,
        this.description,
        this.time,
        this.userId,
        this.date,
        this.hour,
        this.duration,
        this.old,
        ms
    )
}