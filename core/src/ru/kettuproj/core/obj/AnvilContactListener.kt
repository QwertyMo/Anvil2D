package ru.kettuproj.core.obj

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class AnvilContactListener : ContactListener {
    override fun beginContact(contact: Contact?) {

    }

    override fun endContact(contact: Contact?) {
        TODO("Not yet implemented")
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
        TODO("Not yet implemented")
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
        TODO("Not yet implemented")
    }
}