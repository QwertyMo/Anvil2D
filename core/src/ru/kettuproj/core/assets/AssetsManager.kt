package ru.kettuproj.core.assets

import org.apache.log4j.Level
import org.apache.log4j.Logger
import ru.kettuproj.core.assets.atlas.AtlasManager
import ru.kettuproj.core.assets.audio.AudioManager
import ru.kettuproj.core.assets.font.FontManager
import java.io.File
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import kotlin.io.path.*

class AssetsManager {

    companion object{
        const val ASSETS_DIR = "assets"
    }

    private val assets = loadAssets()

    val atlas: AtlasManager = AtlasManager(assets)
    val audio: AudioManager = AudioManager(assets)
    val font : FontManager  = FontManager(assets)


    /**
     * Dispose assets
     *
     * @author QwertyMo
     */
    fun dispose(){
        atlas.disposeAtlases()
        atlas.unloadAllAnimations()
        atlas.unloadAllSprites()
        font.disposeAll()
        audio.disposeAll()
    }

    /**
     * Auto register assets
     *
     * @author QwertyMo
     */
    fun autoRegister(){
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start auto registering assets")
        atlas.autoRegister()
        audio.autoRegister()
        font.autoRegister()
    }

    private fun loadAssets(): List<String>{
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start search all internal assets")
        val uri = this::class.java.getResource("/assets")?.toURI() ?: return listOf()
        var filesystem: FileSystem? = null
        val assetsList = if(uri.scheme.equals("jar")){
            filesystem = FileSystems.newFileSystem(uri, mapOf<String, Any>())
            search(filesystem,"/assets")
        }
        else search(uri.toPath().toString())
        filesystem?.close()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Get ${assetsList.size} assets files")
        return assetsList
    }

    private fun search(filesystem: FileSystem, path: String): List<String>{
        val list = mutableListOf<String>()
        for(entity in filesystem.getPath(path).listDirectoryEntries())
            if(entity.isDirectory()) list.addAll(search(filesystem, "$path/${entity.name}"))
            else list.add(entity.pathString.substring(1))
        return list
    }

    private fun search(path: String): List<String>{
        val list = mutableListOf<String>()
        val file = File(path)
        if(file.list() == null) return emptyList()
        for(entity in file.list()!!)
            if(File("$path/$entity").isDirectory) list.addAll(search("$path/$entity"))
            else list.add("${path}/$entity")
        return list
    }
}