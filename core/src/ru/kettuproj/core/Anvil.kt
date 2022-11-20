package ru.kettuproj.core

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import de.jcm.discordgamesdk.Core
import ru.kettuproj.core.assets.AssetsManager
import ru.kettuproj.core.connections.discord.DiscordAPI
import ru.kettuproj.core.event.EventManager
import ru.kettuproj.core.event.builtin.OnGameRunEvent
import ru.kettuproj.core.input.AnvilInputProcessor
import ru.kettuproj.core.input.InputManager
import ru.kettuproj.core.window.WindowManager
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import kotlin.system.exitProcess


/**
 * Core class. Your game class must extend by this class.
 * Use event manager with OnGameRunEvent() to get this
 * class after loading
 *
 * @author QwertyMo
 */
open class Anvil : Game(){

	companion object{
		/**
		 * Event manager. Use this to call and listen events
		 *
		 * @author QwertyMo
		 */
		val eventManager 	= EventManager()

		/**
		 * Input manager. Can get input device states
		 *
		 * @author QwertyMo
		 */
		val input 			= InputManager()

		/**
		 * Window manager. Manage window settings
		 *
		 * @author QwertyMo
		 */
		val window 			= WindowManager()

		/**
		 * Atlas manager. Load and get atlases with sprites and animations
		 *
		 * @author QwertyMo
		 */
		val assets 	= AssetsManager()

		val discord = DiscordAPI()

		fun exit(){
			exitProcess(0)
		}
	}

	override fun create() {
		Gdx.input.inputProcessor = AnvilInputProcessor()
		window.update()
		eventManager.call(OnGameRunEvent(this))
	}
}