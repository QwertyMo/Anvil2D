package ru.kettuproj.core.event

import kotlin.reflect.KClass

class EventManager {
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<out AnvilEvent>>> = mutableMapOf()

    inline fun <reified T : AnvilEvent> listen(listener: EventListener<T>) {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<out AnvilEvent>> = listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
    }

    inline fun <reified T: AnvilEvent> call(event: T) {
        listeners[event::class]?.asSequence()
            ?.filterIsInstance<EventListener<T>>()
            ?.forEach { it.handle(event) }
    }
}