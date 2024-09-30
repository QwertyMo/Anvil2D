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
 */
class AtlasManager(private val assets: List<String>) {

    /**
     * Store loaded atlases
     */
    private val atlases     : MutableMap<String, TextureAtlas> = mutableMapOf()

    /**
     * Store loaded animations
     */
    private val animations  : MutableMap<String, Array<AtlasRegion>> = mutableMapOf()

    /**
     * Store loaded sprites
     */
    private val sprites     : MutableMap<String, AtlasRegion> = mutableMapOf()


    /**
     * Register texture atlas from assets
     *
     * @param path path to atlas
     * @param name name to link to atlas
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
     * Regions of atlas, which has format ${NAME}_${INDEX} can combine
     * in animation. Index starts from 0
     *
     * @param atlas atlas name, registered earlier
     * @param region region of atlas
     * @param name name to link it to animation
     */
    fun registerAnimation(atlas: String, region: String, name: String){
        try{
            animations[name] = atlases[atlas]!!.findRegions(region).toArray()
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
     */
    fun getAnimation(name: String):Array<AtlasRegion>?{
        return animations[name]
    }

    /**
     * Get sprite from registered sprites
     *
     * @param name sprite name
     *
     * @return atlas region, if it registered
     */
    fun getSprite(name: String):AtlasRegion?{
        if( sprites[name] == null) Logger.getLogger(this.javaClass.name).log(Level.WARN,"Can't find sprite $name")
        return sprites[name]
    }

    /**
     * Remove sprite from sprite list
     *
     * @param name sprite name
     */
    fun unloadSprite(name: String){
        sprites.remove(name)
    }

    /**
     * Remove animation from animation list
     *
     * @param name animation name
     */
    fun unloadAnimation(name: String){
        animations.remove(name)
    }

    /**
     * Dispose atlas
     *
     * @param name atlas name
     */
    fun disposeAtlas(name: String){
        atlases[name]?.dispose()
    }

    /**
     * Remove all sprites
     */
    fun unloadAllSprites(){
        sprites.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All sprites has been unloaded")
    }

    /**
     * Remove all animations
     */
    fun unloadAllAnimations(){
        animations.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All animations has been unloaded")
    }

    /**
     * Dispose all atlases
     */
    fun disposeAtlases(){
        for(i in atlases)
            i.value.dispose()
        atlases.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All atlases has been disposed")
    }
}