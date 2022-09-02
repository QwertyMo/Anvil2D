package ru.kettuproj.core.event

interface EventListener<T: AnvilEvent> {
    fun handle(event: T): Unit
}