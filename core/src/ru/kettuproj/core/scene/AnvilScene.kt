package ru.kettuproj.core.scene

import box2dLight.RayHandler
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ru.kettuproj.core.obj.AnvilObject


open class AnvilScene(
    val ratio: Float = 1f
) : Screen {

    var width = 256f
    var resolution: Vector2                         = Vector2(width, width * ratio)

    val batch:      SpriteBatch                     = SpriteBatch()
    val camera:     OrthographicCamera              = OrthographicCamera()
    val viewport:   Viewport                        = FitViewport(resolution.x,resolution.y,camera)
    val world:      World                           = World(Vector2(0f, 0f), true)

    var cameraPos:  Vector2                         = Vector2(0f,0f)

    val objects:    MutableMap<String, AnvilObject> = mutableMapOf()

    var rayHandler: RayHandler = RayHandler(world)

    private val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer(true,true,true,true,true,true)
    private var tickrate: Int = 20

    init{
        camera.position.set(Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f));
        camera.update();
        viewport.apply()
        rayHandler.useCustomViewport(viewport.screenX,viewport.screenY,viewport.screenWidth, viewport.screenHeight)
        rayHandler.setAmbientLight(.5f);

    }

    fun setTickRate(value: Int){
        tickrate = value
    }

    fun moveCamera(x: Float, y: Float){
        cameraPos.add(x,y)
    }

    fun translateCamera(x: Float, y: Float){
        cameraPos.set(x,y)
    }

    fun updateCamera(){
        camera.position.set(cameraPos.x,cameraPos.y,0f)
        camera.update()
    }

    override fun show(){

    }

    open fun update(delta: Float){
    }

    private var accumulator = 0f
    private var skippedFrames = 0
    private val maxFrameSkip = 20
    private var timeStep = 1 / tickrate.toFloat()

    override fun render(delta: Float) {
        accumulator += delta
        skippedFrames = 0

        while (accumulator >= timeStep && skippedFrames <= maxFrameSkip) {
            accumulator -= timeStep
            world.step(timeStep,3,3)
            for(obj in objects) {
                obj.value.update()
            }
            rayHandler.update()
            updateCamera()
            update(timeStep)
            skippedFrames++
        }
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        rayHandler.setCombinedMatrix(camera)
        batch.projectionMatrix = camera.combined
        batch.begin()
        for(obj in objects)
            obj.value.draw()
        batch.end()
        debugRenderer.render(world,camera.combined)
        rayHandler.renderOnly()

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width,height)
        viewport.apply()
        camera.position.set(cameraPos.x,cameraPos.y,0f)
        camera.update()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        rayHandler.dispose()
        batch.dispose()
        world.dispose()
    }
}