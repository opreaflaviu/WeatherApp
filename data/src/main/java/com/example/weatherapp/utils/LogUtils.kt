package com.example.weatherapp.utils

import android.util.Log

fun Any.logV(message: String, tag: String? = null) = Log.v(buildTag(this.name, tag), message)

fun Any.logE(message: String, error: Throwable, tag: String? = null) = Log.e(buildTag(this.name, tag), message, error)

fun Any.logD(message: String, tag: String? = null) = Log.d(buildTag(this.name, tag), message)

fun Any.logI(message: String, tag: String? = null) = Log.i(buildTag(this.name, tag), message)

fun Any.logW(message: String, tag: String? = null) = Log.w(buildTag(this.name, tag), message)

fun Any.logW(message: String, error: Throwable, tag: String? = null) = Log.w(buildTag(this.name, tag), message, error)

val Any?.simpleName: String
    get() = if (this == null) "null" else this::class.java.simpleName ?: ""

val Any?.canonicalName: String
    get() = if (this == null) "null" else this::class.java.canonicalName ?: ""

val Any?.name: String
    get() = if (this == null) "null" else this::class.java.name ?: ""

private fun buildTag(className: String?, tag: String?): String = when {
    tag.isNullOrEmpty() -> if (className.isNullOrEmpty()) "com.example.weatherapp" else className
    else -> tag
}