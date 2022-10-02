package ru.kettuproj.core.event

//TODO: Add sample
/**
 * Event handler
 *
 * @see ru.kettuproj.core.event.EventManager
 *
 * @author QwertyMo
 */
interface EventListener<T: AnvilEvent> {
    fun handle(event: T): Unit
}