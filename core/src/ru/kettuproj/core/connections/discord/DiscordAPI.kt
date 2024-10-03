package ru.kettuproj.core.connections.discord

import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams
import de.jcm.discordgamesdk.LogLevel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.log4j.Level
import org.apache.log4j.Logger


/**
 * Discord API, that manage discord activity
 */
class DiscordAPI {
    /**
     * State of Discord API. If discord is not
     * installed, and it doesn't run, it will be false
     */
    var isActive: Boolean = false

    /**
     * Discord API core
     */
    var api: Core? = null
        private set

    /**
     * Discord activity from Discord API
     */
    var activity: Activity? = null
        private set


    /**
     * Initialize Discord API
     *
     * @param clientId client ID from app at Discord developer portal
     */
    fun initialize(clientId:Long){
        GlobalScope.launch {
            Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start initializing discord API")
            val params = CreateParams()
            params.clientID = clientId
            params.flags = CreateParams.getDefaultFlags()
            try{
                val core = Core(params)
                core.setLogHook(LogLevel.VERBOSE) { _: LogLevel?, _: String? -> }
                core.let {
                    api = it
                    activity = Activity(it)
                    isActive = true
                    Logger.getLogger(this.javaClass.name).log(Level.INFO, "Discord API initialized successfully")
                }
            }catch (e: Exception){
                isActive = false
                Logger.getLogger(this.javaClass.name).log(Level.WARN, "Discord API is not initialized. Reason: $e")
            }
        }

    }

}