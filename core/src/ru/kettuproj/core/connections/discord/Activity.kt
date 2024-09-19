package ru.kettuproj.core.connections.discord

import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.activity.Activity
import java.time.Instant

class Activity(private val api: Core) {

    /**
     * Discord activity from API
     */
    val activity = Activity()

    /**
     * State at bottom at discord activity
     */
    var state = "Anvil2D engine"
        set(value){
            field = value
            activity.details = value
            api.activityManager().updateActivity(activity)
        }

    /**
     * Main description at discord activity
     */
    var description = "Just dev at Anvil2D"
        set(value){
            field = value
            activity.state = value
            api.activityManager().updateActivity(activity)
        }

    /**
     * Start time of application. Need to show how much time player in
     */
    var startTime: Instant = Instant.now()
        set(value) {
            field = value
            activity.timestamps().start = value
            api.activityManager().updateActivity(activity)
        }

    /**
     * Large image name at Discord API
     */
    var largeImage: String = ""
        set(value){
            field = value
            activity.assets().largeImage = value
            api.activityManager().updateActivity(activity)
        }

    /**
     * Large image description
     */
    var largeImageDescription: String = "desc for large image"
        set(value){
            if(value.length == 1) return
            field = value
            activity.assets().largeText = value
            api.activityManager().updateActivity(activity)
        }


    /**
     * Small image name at Discord API
     */
    var smallImage: String = ""
        set(value){
            field = value
            activity.assets().smallImage = value
            api.activityManager().updateActivity(activity)
        }


    /**
     * Small image description
     */
    var smallImageDescription: String = "desc for large image"
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