package ru.kettuproj.core.obj

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import ru.kettuproj.core.scene.AnvilScene
import java.util.UUID

abstract class AnvilObject(
    private val scene: AnvilScene,
    bodyType: BodyDef.BodyType = BodyDef.BodyType.DynamicBody
) : IAnvilObject {
    val position: Vector2   = Vector2(0f,0f)
    val scale       : Vector2   = Vector2(2f,2f)
    val size        : Vector2   = Vector2(0f,0f)
    var rotation    : Float     = 0f

    var sprite   : TextureAtlas.AtlasRegion? = null
        set(value) {
            setSpriteSettings()
            field = value
        }
    var animation: AnvilAnimation = AnvilAnimation()
    var body    : Body

    val uuid: UUID = UUID.randomUUID()

    private var shape: ObjectShape = ObjectShape.BOX
    private var cSize: Vector2 = Vector2(0f,0f)

    val contacts: MutableList<AnvilObject> = mutableListOf()

    init{
        val bodyDef = BodyDef()
        bodyDef.type = bodyType
        bodyDef.position.set(position.x,position.y)

        body = scene.world.createBody(bodyDef)
    }

    private fun setShape(){

        for(i in body.fixtureList)
            body.destroyFixture(i)

        if(shape == ObjectShape.BOX){
            val shape = PolygonShape()
            shape.setAsBox((cSize.x * scale.x)/2 , (cSize.y * scale.y)/2)
            body.createFixture(shape, 0f)
            shape.dispose()
        }
        else if(shape == ObjectShape.CIRCLE){
            val shape = CircleShape()
            shape.radius = cSize.x
            body.createFixture(shape, 0f)
            shape.dispose()
        }

        body.userData = this
    }

    fun addContact(obj: AnvilObject){
        contacts.add(obj)
    }

    fun removeContact(obj: AnvilObject){
        contacts.remove(obj)
    }

    fun setSpriteCollider(){
        if(sprite!=null){
            shape = ObjectShape.BOX
            cSize.set(size.x,size.y)
            setShape()
        }
    }

    fun setBoxCollider(x: Float, y: Float){
        shape = ObjectShape.BOX
        cSize.set((x),(y))
        setShape()
    }

    fun setSpriteSettings(){
        if(sprite!=null) size.set(sprite!!.regionWidth.toFloat(),sprite!!.regionHeight.toFloat())
    }

    override fun update(){
        position.set(body.position.x, body.position.y)
        //TODO: Maybe animation update need to be move in draw call
        if(animation.state.isNotEmpty() && !animation.stop)
            sprite = animation.update()
    }

    fun rotate(angle: Float){
        body.angularVelocity = angle
        rotation=body.angle * (180f/Math.PI.toFloat())
    }

    fun scale(x: Float, y: Float){
        scale.add(x,y)
        setShape()
    }

    fun move(x: Float, y: Float){
        body.setLinearVelocity(x*10,y*10)
    }

    fun translate(x: Float, y: Float){
        body.setTransform(Vector2(x,y), body.angle)
    }

    override fun draw() {
        if(sprite!=null){
            scene.batch.draw(
                sprite,
                position.x - (size.x)/2,
                position.y - (size.y)/2,
                size.x /2,
                size.y /2,
                size.x,
                size.y,
                scale.x,
                scale.y,
                rotation
                )
        }
    }
}