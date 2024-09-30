package ru.kettuproj.core.obj.type

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import ru.kettuproj.core.assets.AssetsManager
import ru.kettuproj.core.obj.AnvilObject

abstract class TextObject : AnvilObject() {

    private var font: BitmapFont? = null

    var text:  String = ""
        set(value){
            param.characters = text
            field = value
        }

    var scale: Float = 0.5f

    var resolution: Int = 32
        set(value){
            param.size = resolution
            field = value
        }

    var color: Color = Color.WHITE
        set(value){
            param.color = color
            field = value
        }


    val param = FreeTypeFontGenerator.FreeTypeFontParameter()
    init{
        param.characters = text
        param.size = resolution
        param.color = color
        updateFont()
    }

    private fun updateFont(){

    }

    override fun create() {

    }

    override fun draw(delta: Float) {
        font?.dispose()
        val fontGen = FreeTypeFontGenerator(Gdx.files.internal("${AssetsManager.ENGINE_DIR}${AssetsManager.DEFAULT_FONT}"))
        val glyphLayout = GlyphLayout()
        try {
            font = fontGen.generateFont(param)
            glyphLayout.setText(font, text)
        }catch (_:Exception){ }
        fontGen.dispose()
        font?.data?.setScale(scale)

        if(visible) font?.draw(scene.batch, text, (position.x - glyphLayout.width/2), (position.y - glyphLayout.height/2))
        super.draw(delta)
    }
}