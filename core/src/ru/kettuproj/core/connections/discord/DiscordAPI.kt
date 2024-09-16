package ru.kettuproj.core.connections.discord

import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams


class DiscordAPI {
    var isInstalled: Boolean = false

    var api: Core? = null
        private set

    var activity: Activity? = null
        private set

    fun initialize(clientId:Long){

            val params = CreateParams()
            params.clientID = clientId
            params.flags = CreateParams.getDefaultFlags()
            try{
                Core(params).let {
                    api = it
                    activity = Activity(it)
                    isInstalled = true
                }
            }catch (e: Exception){
                isInstalled = false
            }
        }

}