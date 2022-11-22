package ru.kettuproj.core.obj.type

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import ru.kettuproj.core.assets.AssetsManager
import ru.kettuproj.core.obj.AnvilObject

abstract class TextObject : AnvilObject() {

    var text:  String = ""
        set(value){
            glyphLayout.setText(font, value)
            updateFont()
            field = value
        }
    var scale: Float = 0.5f
        set(value) {
            font.data.setScale(value)
            field = value
        }

    var resolution: Int = 32
        set(value){
            field = value
            updateFont()
        }

    var color: Color = Color.WHITE
        set(value){
            field = value
            updateFont()
        }

    private val glyphLayout: GlyphLayout = GlyphLayout()
    private val param = FreeTypeFontGenerator.FreeTypeFontParameter()

    private var fontGen =
        FreeTypeFontGenerator(Gdx.files.internal("${AssetsManager.ENGINE_DIR}${AssetsManager.DEFAULT_FONT}"))

    private var font: BitmapFont = fontGen.generateFont(param)
    private fun updateFont(){
        param.characters = text
        param.size = resolution
        param.color = color
        try { font = fontGen.generateFont(param) }catch (_:Exception){}
        font.data.setScale(scale)
    }

    override fun create() {

    }

    override fun draw() {
        font.draw(scene.batch, text, (position.x - glyphLayout.width/2), (position.y - glyphLayout.height/2))
        super.draw()
    }
}