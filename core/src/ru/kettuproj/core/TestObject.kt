package ru.kettuproj.core

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.scene.AnvilScene

class TestObject(
    scene: AnvilScene
) : AnvilObject(scene) {

    var atlas: TextureAtlas = TextureAtlas("garden_bed.atlas")

    init{
        sprite = atlas.createSprite("dirt")
        setSpriteSettings()
        setSpriteCollider()
    }

}