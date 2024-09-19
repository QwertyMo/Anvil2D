package ru.kettuproj.core.event

import kotlin.reflect.KClass

/**
 * Event manager, allows you to listen and call events
 */
class EventManager {

   /**
    * Listeners list, contains all initialized listeners
    */
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<out AnvilEvent>>> = mutableMapOf()

    /**
     * Event listener. Handle initialized event
     * Check example game to see, how to use this
     */
    inline fun <reified T : AnvilEvent> listen(listener: EventListener<T>) {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<out AnvilEvent>> = listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
    }

    /**
     * Call event, that handled in listener
     * Check example game to see, how to use this
     *
     * @param event AnvilEvent with call params
     *
     * @see ru.kettuproj.core.event.AnvilEvent
     */
    inline fun <reified T: AnvilEvent> call(event: T) {
        listeners[event::class]?.asSequence()
            ?.filterIsInstance<EventListener<T>>()
            ?.forEach { it.handle(event) }
    }
}