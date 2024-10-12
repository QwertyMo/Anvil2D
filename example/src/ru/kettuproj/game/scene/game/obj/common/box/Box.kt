package ru.kettuproj.game.scene.game.obj.common.box

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.BodyDef
import ru.kettuproj.core.obj.type.CollisionObject
import ru.kettuproj.core.obj.type.shape.PolygonObject
import ru.kettuproj.core.obj.type.shape.polygon.Polygon
import ru.kettuproj.game.scene.game.obj.entity.player.PlayerSprite

class Box : CollisionObject() {

    private val sprite = BoxSprite()

    override fun create() {
        polygon = Polygon.SQUARE
        bodyType = BodyDef.BodyType.KinematicBody
        createObject(sprite, "box_collision")
        setSize(20f,20f)
        sprite.size = getSize()
    }

    override fun render() {

    }

    override fun logic() {
        rotation+=1
        sprite.rotation = rotation
    }

}

class BoxSprite: PolygonObject(){
    override fun create() {
        polygon = Polygon.SQUARE
        color = Color.BROWN
    }

    override fun render() {

    }

    override fun logic() {}
}