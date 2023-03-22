package ru.kettuproj.core.obj

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.scene.AnvilScene
import java.util.UUID
import kotlin.math.atan2

/**
 * Anvil object, put on scene. All objects at scene must extend it
 *
 * @author QwertyMo
 */
abstract class AnvilObject: IAnvilObject {

    protected lateinit var scene: AnvilScene
        private set

    open fun setScene(scene: AnvilScene){
        this.scene = scene
        create()
    }

    var name: String? = null

    /**
     * Position of object.
     * To change it, use move() and translate() functions
     *
     * @author QwertyMo
     */
    val position    : Vector2   = Vector2(0f,0f)

    var visible     : Boolean   = true

    /**
     * Position of parent object.
     * Used for better moving and rotating children objects
     *
     * @author QwertyMo
     */
    val parentPos   : Vector2   = Vector2(0f,0f)

    /**
     * Real position object in world
     */
    protected val realPos: Vector2 = Vector2(position.x + parentPos.x, position.y + parentPos.y)

    /**
     * Rotation of object. If you need to rotate object
     * to point, use lookAt() function
     *
     * @author QwertyMo
     */
    open var rotation    : Float     = 0f
        set(value) {
            renderRotation = field
            renderRotationVelocity = value - rotation
            field = value
        }

    /**
     * UUID of object. Randomize it when initialize
     *
     * @author QwertyMo
     */
    val uuid: UUID = UUID.randomUUID()

    /**
     * Velocity of object in current tick. Edited by move() function.
     * Update in update() function
     *
     * @author QwertyMo
     */
    protected val velocity: Vector2 = Vector2(0f,0f)

    private val renderParentPos: Vector2 = Vector2(0f,0f)
    protected var renderRotation: Float = 0f
    protected var renderRotationVelocity: Float = 0f
    private val renderVelocity: Vector2 = Vector2(0f, 0f)
    protected val renderPos: Vector2 = Vector2(0f,0f)
    private var renderDelta: Float = 0f

    /**
     * List of objects, bind to this object
     *
     * @author QwertyMo
     */
    protected val objects: MutableMap<String, AnvilObject> = mutableMapOf()

    protected val addable: MutableMap<String, AnvilObject> = mutableMapOf()

    var dynamicRotation = false

    fun getObject(name: String): AnvilObject?{
        return objects[name]
    }

    fun createObject(obj: AnvilObject, name: String? = null) : AnvilObject{
        obj.setScene(scene)
        obj.name = name
        obj.parentPos.set(position.x, position.y)
        addable[name ?: obj.uuid.toString()] = obj
        return obj
    }

    /**
     * Rotate object to position
     *
     * @param pos Position to rotate
     *
     * @author QwertyMo
     */
    fun lookAt(pos: Vector2){
        rotation = try{
            atan2(realPos.y - pos.y,realPos.x - pos.x) * 180 / Math.PI.toFloat() + 180
        } catch (e: Exception) {
            rotation
        }
    }

    /**
     * Logic update of object
     *
     * @author QwertyMo
     */
    override fun update(){
        logic()
        renderDelta = 0f
        renderRotationVelocity = 0f
        renderVelocity.set(velocity.x, velocity.y)
        if(dynamicRotation) {
            val rotated = Vector2(
                (position.x * MathUtils.cos((rotation * Math.PI / 180f).toFloat())) + (position.y * MathUtils.cos((rotation * Math.PI / 180f).toFloat())),
                (position.y * MathUtils.sin((rotation * Math.PI / 180f).toFloat())) + (position.x * MathUtils.sin((rotation * Math.PI / 180f).toFloat()))
            )
            //renderPos.set(rotated.x + renderParentPos.x, rotated.y + renderParentPos.y)
            translate(position.x + velocity.x, position.y + velocity.y)
            realPos.set(rotated.x + parentPos.x, rotated.y + parentPos.y)
        }
        else {
            //renderPos.set(position.x + renderParentPos.x, position.y + renderParentPos.y)
            //renderRotation = rotation

            translate(position.x + velocity.x, position.y + velocity.y)
            realPos.set(position.x + parentPos.x, position.y + parentPos.y)
        }
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


    /**
     * Move object
     *
     * @param x X-Axis
     * @param y Y-Axis
     *
     * @author QwertyMo
     */
    open fun move(x: Float, y: Float){
        velocity.add(x, y)
    }

    /**
     * Move object
     *
     * @param vel movement velocity
     *
     * @author QwertyMo
     */
    open fun move(vel: Vector2){
        velocity.add(vel.x, vel.y)
    }

    /**
     * Translate object to position
     *
     * @param x X-Axis
     * @param y Y-Axis
     *
     * @author QwertyMo
     */
    open fun translate(x: Float, y: Float){
        position.set(x,y)
    }

    /**
     * Translate object to position
     *
     * @param pos position to translate
     *
     * @author QwertyMo
     */
    open fun translate(pos: Vector2){
        position.set(pos.x, pos.y)
    }

    /**
     * Draw object in scene
     *
     * @author QwertyMo
     */
    override fun draw(delta: Float) {
        var temp = delta * scene.getCurrentTPS()
        renderDelta+=temp
        if(renderDelta>=1){
            temp -= renderDelta - 1
            renderDelta = 1f
        }
        renderRotation += renderRotationVelocity * temp
        renderPos.set(
            ((realPos.x - renderVelocity.x) + renderParentPos.x + renderDelta * renderVelocity.x) - parentPos.x,
            ((realPos.y - renderVelocity.y) + renderParentPos.y + renderDelta * renderVelocity.y) - parentPos.y
        )

        render()
        for(obj in objects) {
            obj.value.renderParentPos.set(renderPos.x, renderPos.y)
            obj.value.draw(delta)
        }
    }

    fun debugPosition() : String{
        return "Object $name with UUID $uuid\n    " +
                "localPos : $position\n    " +
                "parentPos: $parentPos\n    " +
                "renderPos: $renderPos\n    " +
                "realPos  : $realPos\n"
    }
}