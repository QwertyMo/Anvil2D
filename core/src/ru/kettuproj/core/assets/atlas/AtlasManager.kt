package ru.kettuproj.core.assets.atlas

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import ru.kettuproj.core.assets.AssetsManager
import ru.kettuproj.core.exception.AtlasException
import java.io.File
import org.apache.log4j.Level
import org.apache.log4j.Logger

/**
 * Atlas manager class for manage sprites loading
 *
 * @author QwertyMo
 */
class AtlasManager(private val assets: List<String>) {

    private val atlases     : MutableMap<String, TextureAtlas> = mutableMapOf()
    private val animations  : MutableMap<String, com.badlogic.gdx.utils.Array<AtlasRegion>> = mutableMapOf()
    private val sprites     : MutableMap<String, AtlasRegion> = mutableMapOf()


    /**
     * Register texture atlas from assets
     *
     * @param path path to atlas
     * @param name name to link to atlas
     *
     * @author QwertyMo
     */
    fun register(path: String, name: String, autoReg: Boolean = false){
        try{
            atlases[name] = TextureAtlas("${if(autoReg) "" else "${AssetsManager.ASSETS_DIR}/"}$path")
            Logger.getLogger(this.javaClass.name).log(Level.INFO, "Register atlas [$name]")
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARN,"Can't initialize texture atlas $path: ${e.message}")
            throw AtlasException("Can't initialize texture atlas $path: ${e.message}")
        }
    }

    /**
     * Auto registering of atlas assets. After loading dispose all atlases.
     * For names use atlas and regions names
     *
     * @author QwertyMo
     */
    fun autoRegister(){
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start auto registering atlases from internal storage")
        assets.filter { it.lowercase().endsWith(".atlas") }.forEach {
            val used = mutableListOf<String>()
            val name = File(it).nameWithoutExtension
            register(it, name, true)
            atlases[name]?.regions?.forEach {reg ->
                if(reg.index == -1) registerSprite(name, reg.name, reg.name)
                else if(!used.contains(reg.name)){
                    used.add(reg.name)
                    registerAnimation(name, reg.name, reg.name)
                }
            }
        }

    }

    /**
     * Register sprite from atlas
     *
     * @param atlas atlas name, registered earlier
     * @param region region of atlas
     * @param name name to link it to sprite
     *
     * @author QwertyMo
     */
    fun registerSprite(atlas: String, region: String, name: String){
        try{
            sprites[name] = atlases[atlas]!!.findRegion(region)
            Logger.getLogger(this.javaClass.name).log(Level.INFO, "Register sprite [$name]")
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARN,"Can't initialize sprite $name: ${e.message}")
            throw AtlasException("Can't initialize sprite $name: ${e.message}")
        }
    }

    /**
     * Register animation from atlas.
     * Regions of atlas, which has format ${NAME}_${INDEX} can combined
     * in animation. Index starts from 0
     *
     * @param atlas atlas name, registered earlier
     * @param region region of atlas
     * @param name name to link it to animation
     *
     * @author QwertyMo
     */
    fun registerAnimation(atlas: String, region: String, name: String){
        try{
            animations[name] = atlases[atlas]!!.findRegions(region)
            Logger.getLogger(this.javaClass.name).log(Level.INFO, "Register animation [$name]")
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARN,"Can't initialize animation $name: ${e.message}")
            throw AtlasException("Can't initialize sprite $name: ${e.message}")
        }
    }

    /**
     * Get animation from registered animations
     *
     * @param name animation name
     *
     * @return array of atlas regions, if it registered
     *
     * @author QwertyMo
     */
    fun getAnimation(name: String):com.badlogic.gdx.utils.Array<AtlasRegion>?{
        return animations[name]
    }

    /**
     * Get sprite from registered sprites
     *
     * @param name sprite name
     *
     * @return atlas region, if it registered
     *
     * @author QwertyMo
     */
    fun getSprite(name: String):AtlasRegion?{
        if( sprites[name] == null) Logger.getLogger(this.javaClass.name).log(Level.WARN,"Can't find sprite $name")
        return sprites[name]
    }

    /**
     * Remove sprite from sprite list
     *
     * @param name sprite name
     *
     * @author QwertyMo
     */
    fun unloadSprite(name: String){
        sprites.remove(name)
    }

    /**
     * Remove animation from animation list
     *
     * @param name animation name
     *
     * @author QwertyMo
     */
    fun unloadAnimation(name: String){
        animations.remove(name)
    }

    /**
     * Dispose atlas
     *
     * @param name atlas name
     *
     * @author QwertyMo
     */
    fun disposeAtlas(name: String){
        atlases[name]?.dispose()
    }

    /**
     * Remove all sprites
     *
     * @author QwertyMo
     */
    fun unloadAllSprites(){
        sprites.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All sprites has been unloaded")
    }

    /**
     * Remove all animations
     *
     * @author QwertyMo
     */
    fun unloadAllAnimations(){
        animations.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All animations has been unloaded")
    }

    /**
     * Dispose all atlases
     *
     * @author QwertyMo
     */
    fun disposeAtlases(){
        for(i in atlases)
            i.value.dispose()
        atlases.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All atlases has been disposed")
    }
}