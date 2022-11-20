package ru.kettuproj.core.connections.discord

import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams
import de.jcm.discordgamesdk.activity.Activity
import org.apache.log4j.Level
import org.apache.log4j.Logger
import ru.kettuproj.core.exception.DiscordException
import java.io.File
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.time.Instant
import kotlin.io.path.copyTo
import kotlin.io.path.exists


class DiscordAPI {
    var discord: Core? = null

    fun initialize(clientId:Long){
        val path = File(this::class.java.protectionDomain.codeSource.location.toURI()).parentFile.path
        println(path)
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start search all internal assets")
        val uri = this::class.java.getResource("/")?.toURI() ?: throw DiscordException("uri is null")
        var filesystem: FileSystem? = null
        if(uri.scheme.equals("jar")){
            filesystem = FileSystems.newFileSystem(uri, mapOf<String, Any>())
            println(filesystem.getPath("discord").exists())
            filesystem.getPath("discord").copyTo(File(path).toPath())
            //Core.init(filesystem.getPath("discord/discord_game_sdk.dll").toFile())

        }else{
            Core.init(File("C:\\Users\\QwertyMo\\IdeaProjects\\Anvil2D\\core\\src\\main\\resources\\discord\\discord_game_sdk.dll"))
        }
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


}