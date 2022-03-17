package com.zywczas.sliide

import javax.inject.Inject

class SessionManagerImpl @Inject constructor() : SessionManager {

    override suspend fun isNetworkAvailable(): Boolean {
        return true //todo implement
    }

}