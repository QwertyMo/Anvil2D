package ru.kettuproj.core;

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Interpolation.circle
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.ScreenUtils
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.EventManager
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.MouseMoveEvent
import ru.kettuproj.core.input.AnvilInputProcessor
import ru.kettuproj.core.input.InputManager
import ru.kettuproj.core.window.WindowManager


open class Anvil : Game(){

	companion object{
		val eventManager = EventManager()
		val input = InputManager()
		val window = WindowManager()
	}


	override fun create() {
		Gdx.input.inputProcessor = AnvilInputProcessor()
		window.update()

		eventManager.listen(object : EventListener<MouseMoveEvent> {
			override fun handle(event: MouseMoveEvent) {
				//println("x${event.x} y${event.y}")
			}
		})
		setScreen(TestScene())
	}
}