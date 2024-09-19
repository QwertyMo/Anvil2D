package ru.kettuproj.core.assets.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import org.apache.log4j.Level
import org.apache.log4j.Logger
import java.io.File

/**
 * Font manager class for manage fonts loading
 */
class FontManager(private val assets: List<String>) {
    private val fonts: MutableMap<String, FreeTypeFontGenerator> = mutableMapOf()

    /**
     * Register font from assets
     *
     * @param path path to font
     * @param name name
     */
    fun register(path: String, name: String){
        fonts[name] = FreeTypeFontGenerator(Gdx.files.internal(path))
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Register font [$name]")
    }

    /**
     * Auto registering of fonts. For names use fonts names.
     * Font names must not be duplicated, otherwise they will overwrite each other
     */
    fun autoRegister(){
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start auto registering fonts from internal storage")
        assets.filter { it.lowercase().endsWith(".ttf") }.forEach {
            register(it, File(it).nameWithoutExtension)
        }
    }


    /**
     * Get font by name
     *
     * @param name name
     */
    fun getFont(name: String): FreeTypeFontGenerator? {
        return fonts[name]
    }

    /**
     * Dispose all fonts
     *
     * @author QwertyMo
     */
    fun disposeFont(name: String){
        fonts[name]?.dispose()
        fonts.remove(name)
    }

    /**
     * Dispose all fonts
     *
     * @author QwertyMo
     */
    fun disposeAll(){
        fonts.forEach{
            it.value.dispose()
        }
        fonts.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All fonts has been unloaded")
    }
}