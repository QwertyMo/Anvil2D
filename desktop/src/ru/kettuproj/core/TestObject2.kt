package ru.kettuproj.core

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.scene.AnvilScene
import kotlin.math.atan2

class TestObject2(
    private val scene: AnvilScene,
    bodyType: BodyDef.BodyType = BodyDef.BodyType.StaticBody)
    : AnvilObject(scene, bodyType) {

    init{

        sprite = Anvil.atlasManager.getSprite("dirt")
        setBoxCollider(16f,16f)
        setSpriteSettings()
        setSpriteCollider()

    }

    override fun update() {
        super.update()
        translate(scene.getCursor().x, scene.getCursor().y)

    }


}