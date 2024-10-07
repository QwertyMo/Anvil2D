package ru.kettuproj.core.obj.type

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import ru.kettuproj.core.exception.AnvilObjectException
import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.obj.ObjectShape
import ru.kettuproj.core.scene.AnvilScene

/**
 * Collision object, which interact with Box2D, and have physics.
 * At enabled debug option, it will draw it own hitboxes
 *
 * Use when you want to make collision of object
 */
abstract class CollisionObject : AnvilObject() {

    /**
     * Object scale. Multiply size
     */
    private val scale       : Vector2   = Vector2(1f,1f)

    /**
     * Get scale of collision
     *
     * @return Scale of collision
     */
    fun getScale():Vector2{
        return Vector2(scale.x, scale.y)
    }

    /**
     * Set scale of collision
     *
     * @param x x scale of collision
     * @param y y scale of collision
     */
    fun setScale(x: Float, y: Float){
        this.scale.set(x, y)
    }

    /**
     * Set scale of collision
     *
     * @param scale Scale of collision
     */
    fun setScale(scale: Vector2){
        this.scale.set(scale.x, scale.y)
    }

    /**
     * Size of collision
     */
    private val size        : Vector2 = Vector2(0f,0f)

    /**
     * Get size of collision
     *
     * @return Size of collision
     */
    fun getSize():Vector2{
        return Vector2(size.x, size.y)
    }

    /**
     * Set size of collision
     *
     * @param x x size of collision
     * @param y y size of collision
     */
    fun setSize(x: Float, y: Float){
        this.size.set(x, y)
        setShape()
    }

    /**
     * Set size of collision
     *
     * @param size Size of collision
     */
    fun setSize(size: Vector2){
        this.size.set(size.x, size.y)
        setShape()
    }

    /**
     * Box2D body of object
     */
    var body: Body? = null
        private set


    override fun setScene(scene: AnvilScene) {
        val bodyDef = BodyDef()
        bodyDef.type = bodyType
        bodyDef.position.set(position.x,position.y)
        body = scene.world.createBody(bodyDef)
        setShape()
        super.setScene(scene)
    }

    /**
     * Box2D shape of object
     */
    private var shape: ObjectShape = ObjectShape.BOX
        set(value) {
            setShape()
            field = value
        }

    /**
     * Collider contacts
     */
    val contacts: MutableList<AnvilObject> = mutableListOf()

    /**
     * Type of body
     */
    var bodyType: BodyDef.BodyType = BodyDef.BodyType.DynamicBody
        set(value) {
            setShape()
            field = value
        }


    /**
     * Set collider Box2D shape
     */
    private fun setShape(){

        if(body == null) throw AnvilObjectException("Can't set shape of null body. Maybe scene is null?", this)

        for(i in body!!.fixtureList)
            body!!.destroyFixture(i)

        //TODO: Переделать на полигоны
        if(shape == ObjectShape.BOX){
            val shape = PolygonShape()
            shape.setAsBox((size.x * scale.x)/2 , (size.y * scale.y)/2)
            body!!.createFixture(shape, 0f)
            shape.dispose()
        }
        else if(shape == ObjectShape.CIRCLE){
            val shape = CircleShape()
            shape.radius = size.x
            body!!.createFixture(shape, 0f)
            shape.dispose()
        }

        body!!.userData = this
    }

    override fun translate(pos: Vector2) {
        super.translate(pos)
        body?.setTransform(Vector2(position.x, position.y), rotation)
    }

    override fun translate(x: Float, y: Float) {
        super.translate(x, y)
        body?.setTransform(Vector2(position.x, position.y), rotation)
    }

    override fun update() {
        logic()
        renderDelta = 0f
        renderVelocity.set(velocity.x, velocity.y)
        body?.setLinearVelocity(velocity.x * (scene.moveMultiplier), velocity.y * (scene.moveMultiplier))
        position.set((body?.position?.x ?: 0f) - parentPos.x, (body?.position?.y?: 0f) - parentPos.y)
        realPos.set(body?.position?.x ?: 0f, body?.position?.y ?: 0f)
        for(obj in objects) {
            obj.value.parentPos.set(realPos.x, realPos.y)
            obj.value.update()
        }
        for(obj in addable){
            objects[obj.key] = obj.value
        }
        addable.clear()
        velocity.set(0f,0f)
    }
}