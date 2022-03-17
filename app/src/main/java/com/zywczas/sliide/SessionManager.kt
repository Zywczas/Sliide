package com.zywczas.sliide

interface SessionManager {

    suspend fun isNetworkAvailable() : Boolean

}