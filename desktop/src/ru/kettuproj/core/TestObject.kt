package ru.kettuproj.core

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.physics.box2d.BodyDef
import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.scene.AnvilScene

class TestObject(
    scene: AnvilScene,
    type: BodyDef.BodyType = BodyDef.BodyType.DynamicBody
) : AnvilObject(scene, type) {

    //var atlas: TextureAtlas = TextureAtlas("garden_bed.atlas")

    init{
        //sprite = atlas.createSprite("dirt")
        setBoxCollider(16f,16f)
        setSpriteSettings()
        setSpriteCollider()


    }

    override fun update() {

    }

}