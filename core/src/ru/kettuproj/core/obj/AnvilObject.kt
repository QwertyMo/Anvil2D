package ru.kettuproj.core.obj

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import ru.kettuproj.core.scene.AnvilScene

abstract class AnvilObject(
    val scene: AnvilScene
) : IAnvilObject {

    val position    : Vector2   = Vector2(0f,0f)
    val scale       : Vector2   = Vector2(2f,2f)
    val size        : Vector2   = Vector2(0f,0f)
    var rotation    : Float     = 0f

    var sprite  : Sprite? = null
    var body    : Body

    private var shape: ObjectShape = ObjectShape.BOX
    private var cSize: Vector2 = Vector2(0f,0f)

    init{
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
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
        cSize.set((x)/2,(y)/2)
        setShape()
    }

    fun setSpriteSettings(){
        if(sprite!=null) size.set(sprite!!.width,sprite!!.height)
    }

    override fun update(){

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
        position.set(body.position.x, body.position.y)
    }

    fun translate(x: Float, y: Float){
        body.position.set(x,y)
    }

    //	public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
    //		float scaleX, float scaleY, float rotation) {
    //
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