package ru.kettuproj.core.obj.type.shape

import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.common.ShapeRenderer
import ru.kettuproj.core.common.calcAngle
import ru.kettuproj.core.obj.type.ShapeObject
import kotlin.math.cos
import kotlin.math.sin

open class PolygonObject : ShapeObject() {

    var polygon: FloatArray = floatArrayOf()

    private var compiledPolygon: FloatArray = floatArrayOf()

    private fun updatePolygon(){
        compiledPolygon = polygon.mapIndexed{ index, v ->
            val point = Vector2(0f,0f)
            if(index%2==0){
                point.y = polygon[index + 1] + renderPos.y + (size.y/2 * if(polygon[index + 1]<0) -1 else 1)
                point.x = v + renderPos.x + (size.x/2 * if(v<0) -1 else 1)
                renderPos.x + (point.x - renderPos.x) * cos(calcAngle(renderRotation)) - (point.y - renderPos.y) * sin(calcAngle(renderRotation))
            }else{
                point.y = v + renderPos.y + (size.x/2 * if(v<0) -1 else 1)
                point.x = polygon[index - 1] + renderPos.x + (size.x/2 * if(polygon[index - 1]<0) -1 else 1)
               renderPos.y + (point.x - renderPos.x) * sin(calcAngle(renderRotation)) + (point.y - renderPos.y) * cos(calcAngle(renderRotation))
            }
        }.toFloatArray()
    }

    override fun render() {

    }

    override fun logic() {

    }

    override fun draw(delta: Float){
        super.draw(delta)
        updatePolygon()
        if(compiledPolygon.size>=6 && compiledPolygon.size%2==0) {
            scene.batch.end()
            scene.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
            scene.shapeRenderer.color = color
            scene.shapeRenderer.polygon(compiledPolygon)
            scene.shapeRenderer.end()
            scene.batch.begin()
        }
    }

}