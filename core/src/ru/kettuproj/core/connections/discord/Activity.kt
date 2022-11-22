package ru.kettuproj.core.connections.discord

import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.activity.Activity
import java.time.Instant

class Activity(private val api: Core) {

    val activity = Activity()

    var state = "Anvil2D engine"
        set(value){
            field = value
            activity.details = value
            api.activityManager().updateActivity(activity)
        }

    var description = ""
        set(value){
            field = value
            activity.state = value
            api.activityManager().updateActivity(activity)
        }

    var startTime: Instant = Instant.now()
        set(value) {
            field = value
            activity.timestamps().start = value
            api.activityManager().updateActivity(activity)
        }

    fun show(){
        activity.state = description
        activity.details = state
        activity.timestamps().start = startTime
        api.activityManager().updateActivity(activity)
    }

    init{
        show()
    }
}