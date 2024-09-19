package ru.kettuproj.core

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import ru.kettuproj.core.assets.AssetsManager
import ru.kettuproj.core.connections.discord.DiscordAPI
import ru.kettuproj.core.event.EventManager
import ru.kettuproj.core.event.builtin.OnGameRunEvent
import ru.kettuproj.core.input.AnvilInputProcessor
import ru.kettuproj.core.input.InputManager
import ru.kettuproj.core.window.WindowManager
import kotlin.system.exitProcess


/**
 * Core class. Your game class must extend by this class.
 * Use event manager with OnGameRunEvent() to get this
 * class after loading
 */
open class Anvil : Game(){

	companion object{
		/**
		 * Event manager. Use this to call and listen events
		 */
		val eventManager 	= EventManager()

		/**
		 * Input manager. Can get input device states
		 */
		val input 			= InputManager()

		/**
		 * Window manager. Manage window settings
		 */
		val window 			= WindowManager()

		/**
		 * Atlas manager. Load and get atlases with sprites and animations
		 */
		val assets 	= AssetsManager()

		/**
		 * Discord activity API. Manage discord activity
		 */
		val discord = DiscordAPI()

		/**
		 * Close function of game
		 */
		fun exit(){
			exitProcess(0)
		}
	}

	/**
	 * Create function. Initialize controls, window, and start onGameRun event
	 */
	override fun create() {
		Gdx.input.inputProcessor = AnvilInputProcessor()
		window.update()
		eventManager.call(OnGameRunEvent(this))
	}
}