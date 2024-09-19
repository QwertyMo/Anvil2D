package ru.kettuproj.core.event

/**
 * Event handler. Check example game to see, how to use this
 *
 * @see ru.kettuproj.core.event.EventManager
 */
interface EventListener<T: AnvilEvent> {
    fun handle(event: T)
}