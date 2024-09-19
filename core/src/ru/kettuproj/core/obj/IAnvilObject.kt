package ru.kettuproj.core.obj

/**
 * AnvilObject interface
 */
interface IAnvilObject {

    fun update()

    fun draw(delta: Float)

    fun render()

    fun logic()

    fun create()
}