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

abstract class CollisionObject : AnvilObject() {


    /**
     * Object scale
     *
     * @author QwertyMo
     */
    val scale       : Vector2   = Vector2(1f,1f)

    /**
     * Size of object
     *
     * @author QwertyMo
     */
    val size        : Vector2 = Vector2(0f,0f)


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
     * Collider Box2D size of object
     */
    private var cSize: Vector2 = Vector2(size.x, size.y)
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
     *
     * @author QwertyMo
     */
    private fun setShape(){

        if(body == null) throw AnvilObjectException("Can't set shape of null body. Maybe scene is null?", this)

        for(i in body!!.fixtureList)
            body!!.destroyFixture(i)

        if(shape == ObjectShape.BOX){
            val shape = PolygonShape()
            shape.setAsBox((cSize.x * scale.x)/2 , (cSize.y * scale.y)/2)
            body!!.createFixture(shape, 0f)
            shape.dispose()
        }
        else if(shape == ObjectShape.CIRCLE){
            val shape = CircleShape()
            shape.radius = cSize.x
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