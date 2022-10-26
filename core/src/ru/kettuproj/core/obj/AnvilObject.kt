package ru.kettuproj.core.obj

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import ru.kettuproj.core.obj.light.AnvilLight
import ru.kettuproj.core.scene.AnvilScene
import java.util.UUID
import kotlin.math.atan2

/**
 * Anvil object, put on scene. All objects at scene must extend it
 */
abstract class AnvilObject(
    private val scene: AnvilScene,
    bodyType: BodyDef.BodyType = BodyDef.BodyType.DynamicBody
) : IAnvilObject {

    /**
     * Position of object in world
     */
    val position    : Vector2   = Vector2(0f,0f)

    /**
     * Object scale
     */
    val scale       : Vector2   = Vector2(2f,2f)

    /**
     * Size of object
     */
    val size        : Vector2   = Vector2(0f,0f)

    /**
     * Rotation of object
     */
    var rotation    : Float     = 0f
        set(value) {
            body.setTransform(position.x,position.y, (value/180 * Math.PI).toFloat())
            field=body.angle * (180f/Math.PI.toFloat())
        }

    /**
     * Sprite of object
     * Automatically set size of object
     */
    var sprite   : TextureAtlas.AtlasRegion? = null
        set(value) {
            field = value
            setSpriteSettings()
        }

    /**
     * Animation of object
     */
    var animation: AnvilAnimation = AnvilAnimation()

    /**
     * Box2D body of object
     */
    var body     : Body
        private set


    /**
     * UUID of object
     */
    val uuid: UUID = UUID.randomUUID()


    /**
     * Box2D shape of object
     */
    private var shape: ObjectShape = ObjectShape.BOX

    /**
     * Collider Box2D size of object
     */
    private var cSize: Vector2 = Vector2(0f,0f)

    /**
     * Light system of object
     */
    val light: AnvilLight


    /**
     * Collider contacts
     */
    val contacts: MutableList<AnvilObject> = mutableListOf()

    init{
        val bodyDef = BodyDef()
        bodyDef.type = bodyType
        bodyDef.position.set(position.x,position.y)
        body = scene.world.createBody(bodyDef)
        light = AnvilLight(scene, body)
    }


    /**
     * Set collider Box2D shape
     */
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

    /**
     * Rotate object to position
     */
    fun lookAt(pos: Vector2){
        rotation = atan2(position.y - pos.y,position.x - pos.x) * 180 / Math.PI.toFloat() + 180
    }

    fun addContact(obj: AnvilObject){
        contacts.add(obj)
    }

    fun removeContact(obj: AnvilObject){
        contacts.remove(obj)
    }

    /**
     * Set box collider with sprite size
     */
    fun setSpriteCollider(){
        if(sprite!=null){
            shape = ObjectShape.BOX
            cSize.set(size.x,size.y)
            setShape()
        }
    }

    /**
     * Set box collider ob object
     *
     * @param x width of collider
     * @param y height of collider
     */
    fun setBoxCollider(x: Float, y: Float){
        shape = ObjectShape.BOX
        cSize.set((x),(y))
        setShape()
    }

    /**
     * Set sprite settings
     * Get size of sprite, and set it to object
     */
    fun setSpriteSettings(){
        if(sprite!=null) size.set(sprite!!.regionWidth.toFloat(),sprite!!.regionHeight.toFloat())
    }

    /**
     * Logic update of object
     */
    override fun update(){
        position.set(body.position.x, body.position.y)
        //TODO: Maybe animation update need to be move in draw call
        if(animation.state.isNotEmpty() && !animation.stop)
            sprite = animation.update()
    }

    /**
     * Rotate object in degree
     */
    fun rotate(angle: Float){
        body.angularVelocity = angle
        rotation=body.angle * (180f/Math.PI.toFloat())
    }

    /**
     * Scale object
     *
     * @param x Scale width
     * @param y Scale height
     */
    fun scale(x: Float, y: Float){
        scale.add(x,y)
        setShape()
    }

    /**
     * Move object
     *
     * @param x X-Axis
     * @param y Y-Axis
     */
    fun move(x: Float, y: Float){
        body.setLinearVelocity(x*scene.moveMultiplier,y*scene.moveMultiplier)
    }

    /**
     * Move object
     *
     * @param velocity movement velocity
     */
    fun move(velocity: Vector2){
        body.setLinearVelocity(velocity.x*scene.moveMultiplier,velocity.y*scene.moveMultiplier)
    }

    /**
     * Translate object to position
     *
     * @param x X-Axis
     * @param y Y-Axis
     */
    fun translate(x: Float, y: Float){
        body.setTransform(Vector2(x,y), body.angle)
    }

    /**
     * Translate object to position
     *
     * @param pos position to translate
     */
    fun translate(pos: Vector2){
        body.setTransform(pos, body.angle)
    }

    /**
     * Draw object in scene
     */
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