package ru.kettuproj.core.event

import kotlin.reflect.KClass

/**
 * Event manager, allows you to listen and call events
 *
 * @author QwertyMo
 */
class EventManager {

   /**
    * Listeners list, contains all initialized listeners
    */
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<out AnvilEvent>>> = mutableMapOf()

    //TODO: Add param and sample to listener in EventManager
    /**
     * Event listener. Handle initialized event
     *
     * @author QwertyMo
     */
    inline fun <reified T : AnvilEvent> listen(listener: EventListener<T>) {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<out AnvilEvent>> = listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
    }

    //TODO: Add sample to call in EventManager
    /**
     * Call event, that handled in listener
     *
     * @param event AnvilEvent with call params
     *
     * @see ru.kettuproj.core.event.AnvilEvent
     *
     * @author QwertyMo
     */
    inline fun <reified T: AnvilEvent> call(event: T) {
        listeners[event::class]?.asSequence()
            ?.filterIsInstance<EventListener<T>>()
            ?.forEach { it.handle(event) }
    }
}