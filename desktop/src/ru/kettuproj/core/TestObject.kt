package ru.kettuproj.core

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.MouseMoveEvent
import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.scene.AnvilScene
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.sqrt

class TestObject(
    private val scene: AnvilScene,
    type: BodyDef.BodyType = BodyDef.BodyType.DynamicBody
) : AnvilObject(
    scene,
    type) {

    init{
        Anvil.eventManager.listen(object : EventListener<InputEvent> {
            override fun handle(event: InputEvent) {

            }
        })

        light.setConeLight()
        //animation.addState("run", Anvil.atlasManager.getAnimation("test")!!)
        //animation.state = "run"
        sprite = Anvil.atlasManager.getSprite("dirt")
        setBoxCollider(16f,16f)
        setSpriteSettings()
        setSpriteCollider()
        light.distance = 1000f


    }

    var u = 0
    var m = true
    override fun update() {
        super.update()
        lookAt(scene.getCursor())
        println("$position ${scene.getCursor()}")
        scene.translateCamera(position)
    }

}