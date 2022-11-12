package ru.kettuproj.core.obj.type

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import ru.kettuproj.core.obj.AnvilObject

abstract class TextObject : AnvilObject() {

    var text:  String = ""
        set(value){
            glyphLayout.setText(font, value)
            field = value
        }
    var scale: Float  = 1f
        set(value) {
            font.data.setScale(value)
            field = value
        }

    private val glyphLayout: GlyphLayout = GlyphLayout()

    private val param = FreeTypeFontGenerator.FreeTypeFontParameter()

    private var font: BitmapFont =
        FreeTypeFontGenerator(Gdx.files.internal("lobster.ttf")).generateFont(param)


    override fun create() {
        param.borderColor = Color.WHITE
        param.borderWidth = 1f
        param.size = 256
        param.color = Color.CYAN
        font = FreeTypeFontGenerator(Gdx.files.internal("lobster.ttf")).generateFont(param)
        scale = 0.08f
        println("test")
    }

    override fun draw() {
        font.draw(scene.batch, text, (position.x - glyphLayout.width/2), (position.y - glyphLayout.height/2))
        super.draw()
    }
}