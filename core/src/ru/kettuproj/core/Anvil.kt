package ru.kettuproj.core

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import ru.kettuproj.core.atlas.AtlasManager
import ru.kettuproj.core.event.EventManager
import ru.kettuproj.core.event.builtin.OnGameRunEvent
import ru.kettuproj.core.input.AnvilInputProcessor
import ru.kettuproj.core.input.InputManager
import ru.kettuproj.core.scene.SceneManager
import ru.kettuproj.core.window.WindowManager

open class Anvil : Game(){

	companion object{
		val eventManager 	= EventManager()
		val input 			= InputManager()
		val window 			= WindowManager()
		val sceneManager 	= SceneManager()
		val atlasManager 	= AtlasManager()
	}

	override fun create() {
		sceneManager.initialize(this)
		Gdx.input.inputProcessor = AnvilInputProcessor()
		window.update()
		eventManager.call(OnGameRunEvent(this))
	}
}