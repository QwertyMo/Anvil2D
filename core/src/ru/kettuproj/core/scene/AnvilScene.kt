package ru.kettuproj.core.scene

import box2dLight.RayHandler
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.AnvilObject
import kotlin.math.roundToInt

/**
 * AnvilScene class for make game scene.
 * All scened need to extend by this
 *
 * @param ratio scene ratio
 *
 * @author QwertyMo
 */
open class AnvilScene(
    private val ratio: Float = 1f
) : Screen {

    var moveMultiplier  : Float = 100f
    var width           : Float = 1024f
        set(value){
            resolution = Vector2(value, value * ratio)
            viewport.setWorldSize(resolution.x, resolution.y)
            field = value
        }

    var resolution      : Vector2                         = Vector2(width, width * ratio)
    val batch           : SpriteBatch                     = SpriteBatch()
    val world           : World                           = World(Vector2(0f, 0f), true)
    var rayHandler      : RayHandler                      = RayHandler(world)
    val shapeRenderer   : ShapeRenderer                   = ShapeRenderer()


    val camera      : OrthographicCamera              = OrthographicCamera()
    private val viewport    : Viewport                        = FitViewport(resolution.x,resolution.y,camera)
    private val cameraPos   : Vector2                         = Vector2(0f,0f)

    private val objects     : MutableMap<String, AnvilObject> = mutableMapOf()
    private val addable     : MutableMap<String, AnvilObject> = mutableMapOf()

    //Update tick rate variables
    private var tickrate        : Int   = 10
    private var accumulator     : Float = 0f
    private var skippedFrames   : Int   = 0
    private val maxFrameSkip    : Int   = 20
    private var timeStep        : Float = 1 / tickrate.toFloat()

    private var deltaTPSAccumulator : Float = 0f
    private var deltaTPSUpdates     : Int   = 0
    private var currentTPS          : Float = tickrate.toFloat()

    private var deltaFPSAccumulator : Float = 0f
    private var deltaFPSUpdates     : Int   = 0
    private var currentFPS          : Int   = 0

    private val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer(
        true,
        true,
        true,
        true,
        true,
        true
    )

    init{
        camera.position.set(Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f))
        camera.update()
        viewport.apply()
    }

    fun getObject(name: String): AnvilObject?{
        return objects[name]
    }

    fun createObject(obj: AnvilObject, name: String? = null) : AnvilObject{
        obj.setScene(this)
        obj.name = name
        addable[name ?: obj.uuid.toString()] = obj
        return obj
    }

    /**
     * Zoom camera at value
     *
     * @param value zoom value
     *
     * @author QwertyMo
     */
    fun zoom(value: Float){
        camera.zoom+=value
    }

    /**
     * Set camera zoom
     *
     * @param value zoom value
     *
     * @author QwertyMo
     */
    fun setZoom(value: Float){
        camera.zoom = value
    }

    /**
     * Get cursor position in world
     *
     * @return cursor position
     *
     * @author QwertyMo
     */
    fun getCursor():Vector2{
        return Vector2(
            ((Anvil.input.onScreenCursor.x - Gdx.graphics.width  / 2)) * (resolution.x / Gdx.graphics.width ) * camera.zoom + cameraPos.x,
            ((Anvil.input.onScreenCursor.y - Gdx.graphics.height / 2)) * (resolution.y / Gdx.graphics.height) * camera.zoom + cameraPos.y
        )
    }

    /**
     * Set tick rate on scene
     *
     * @param value tickrate
     *
     * @author QwertyMo
     */
    fun setTickRate(value: Int){
        tickrate = value
        timeStep = 1 / tickrate.toFloat()
    }

    fun getTickRate():Int{
        return tickrate
    }

    fun getFrameRate():Int{
        return 0
    }

    /**
     * Move camera from it position
     *
     * @param x X-Axis
     * @param y Y-Axis
     *
     * @author QwertyMo
     */
    fun moveCamera(x: Float, y: Float){
        cameraPos.add(x,y)
    }

    /**
     * Move camera from it position
     *
     * @param velocity velocity
     *
     * @author QwertyMo
     */
    fun moveCamera(velocity: Vector2){
        cameraPos.add(velocity.x,velocity.y)
    }

    /**
     * Translate camera to position
     *
     * @param x X-Axis
     * @param y Y-Axis
     *
     * @author QwertyMo
     */
    fun translateCamera(x: Float, y: Float){
        cameraPos.set(x,y)
    }

    /**
     * Translate camera to position
     *
     * @param pos position where need to translate camera
     *
     * @author QwertyMo
     */
    fun translateCamera(pos: Vector2){
        cameraPos.set(pos.x,pos.y)
    }

    /**
     * Update camera
     *
     * @author QwertyMo
     */
    private fun updateCamera(){
        camera.position.set(cameraPos.x,cameraPos.y,0f)
        camera.update()
    }

    /**
     * Render objects and rays
     *
     * @author QwertyMo
     */
    private fun renderScene(delta: Float){
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        renderObjects(delta)
        renderRays()
        renderDebug()
    }

    /**
     * Debug render of Box2D colliders
     *
     * @author QwertyMo
     */
    private fun renderDebug(){
        debugRenderer.render(world,camera.combined)
    }

    /**
     * Light rendering
     *
     * @author QwertyMo
     */
    private fun renderRays(){
        rayHandler.useCustomViewport(viewport.screenX,viewport.screenY,viewport.screenWidth, viewport.screenHeight)
        rayHandler.setCombinedMatrix(camera)
        rayHandler.render()
    }

    /**
     * Object rendering
     *
     * @author QwertyMo
     */
    private fun renderObjects(delta: Float){
        batch.projectionMatrix = camera.combined
        shapeRenderer.projectionMatrix = batch.projectionMatrix
        shapeRenderer.transformMatrix = batch.transformMatrix
        batch.begin()
        for(obj in objects)
            obj.value.draw(delta)
        batch.end()
    }

    fun getCurrentTPS(): Float{
        return currentTPS
    }

    fun getCurrentFPS():Int{
        return currentFPS
    }

    /**
     * Game update loop. Update render, and try to update logic
     *
     * @param delta delta time
     *
     * @author QwertyMo
     */
    override fun render(delta: Float) {
        Anvil.discord.api?.runCallbacks()
        accumulator += delta
        skippedFrames = 0
        deltaTPSAccumulator+=delta
        deltaFPSAccumulator+=delta
        deltaFPSUpdates++
        if(deltaFPSAccumulator>=1){
            currentFPS = deltaFPSUpdates
            deltaFPSUpdates = 0
            deltaFPSAccumulator = 0f
        }
        while (accumulator >= timeStep && skippedFrames <= maxFrameSkip) {
            deltaTPSUpdates++
            if(deltaTPSAccumulator>=1){
                currentTPS = ((deltaTPSUpdates - ((deltaTPSAccumulator - 1) * (deltaTPSUpdates))) * 100f).roundToInt() / 100f
                deltaTPSAccumulator = 0f
                deltaTPSUpdates = 0
            }
            updateState()
        }
        renderScene(delta)
    }

    /**
     * Update game logic
     *
     * @author QwertyMo
     */
    private fun updateState(){
        accumulator -= timeStep
        world.step(timeStep,8,3)
        for(obj in objects) {
            obj.value.update()
        }
        for(obj in addable){
            objects[obj.key] = obj.value
        }
        addable.clear()
        rayHandler.update()
        updateCamera()
        update(timeStep)
        skippedFrames++
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width,height)
        viewport.apply()
        camera.position.set(cameraPos.x,cameraPos.y,0f)
        camera.update()
    }

    open fun update(delta: Float){}

    override fun show(){}

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        rayHandler.dispose()
        batch.dispose()
        world.dispose()
    }
}