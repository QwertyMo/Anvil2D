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

    var largeImage: String = ""
        set(value){
            field = value
            activity.assets().largeImage = value
            api.activityManager().updateActivity(activity)
        }

    var largeImageDescription: String = ""
        set(value){
            if(value.length == 1) return
            field = value
            activity.assets().largeText = value
            api.activityManager().updateActivity(activity)
        }

    var smallImage: String = ""
        set(value){
            field = value
            activity.assets().smallImage = value
            api.activityManager().updateActivity(activity)
        }

    var smallImageDescription: String = ""
        set(value){
            if(value.length == 1) return
            field = value
            activity.assets().smallText = value
            api.activityManager().updateActivity(activity)
        }


    init{
        activity.state = description
        activity.details = state
        activity.timestamps().start = startTime
        activity.assets().largeText = largeImageDescription
        activity.assets().smallText = smallImageDescription
        api.activityManager().updateActivity(activity)
    }
}