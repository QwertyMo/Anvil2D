package ru.kettuproj.core.connections.discord

import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams
import de.jcm.discordgamesdk.activity.Activity
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.SystemUtils
import ru.kettuproj.core.exception.DiscordException
import java.io.File
import java.time.Instant
import kotlin.io.path.*


class DiscordAPI {
    var discord: Core? = null

    fun initialize(clientId:Long){
        initCore()
        val params = CreateParams()
        params.clientID = clientId
        params.flags = CreateParams.getDefaultFlags()
        discord = Core(params)
        val activity = Activity()
        activity.details = "Discord test"
        activity.state = "..."
        activity.timestamps().start = Instant.now()
        discord!!.activityManager().updateActivity(activity)
    }

    private fun initCore(){
        val path = File(this::class.java.protectionDomain.codeSource.location.toURI()).parentFile.path
        val uri = this::class.java.getResource("/discord")?.toURI() ?: throw DiscordException("uri is null")
        val extension =
            if(SystemUtils.IS_OS_WINDOWS) ".dll"
            else if(SystemUtils.IS_OS_LINUX) ".so"
            else if(SystemUtils.IS_OS_MAC) ".dylib"
            else return
        if(uri.scheme.equals("jar")) {
            val file = File("$path/data/discord_game_sdk${extension}")
            val dir = File("$path/data")
            if (!dir.exists()) dir.mkdir()
            if (!file.exists()) file.createNewFile()
            val stream = this::class.java.getResourceAsStream("/discord/discord_game_sdk${extension}")
            FileUtils.copyInputStreamToFile(stream, file)
            stream?.close()
            Core.init(file)
        }else{
            Core.init(File("${uri.toPath()}/discord_game_sdk${extension}"))
        }
    }


}