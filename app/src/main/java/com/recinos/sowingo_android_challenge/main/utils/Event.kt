package com.recinos.sowingo_android_challenge.main.utils

import androidx.annotation.MainThread

open class Event<out T>(private val content: T) {
    private val consumedScopes = HashSet<String>()

    fun isConsumed(scope: String = "") = consumedScopes.contains(scope)

    @MainThread
    fun consume(scope: String = ""): T? {
        return if (isConsumed(scope)) {
            null
        } else {
            consumedScopes.add(scope)
            content
        }
    }

    fun peek(): T = content
}