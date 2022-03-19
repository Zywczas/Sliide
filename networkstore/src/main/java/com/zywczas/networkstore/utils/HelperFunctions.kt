package com.zywczas.networkstore.utils
//todo comment for reviewers:
//I have encrypted the file with the 'API_KEY' in utils/Constants.kt.gpg.
//To use the app it's enough to replace `$API_KEY` with your own key or use an .apk file from my Github repository
internal fun getBearer(): String = "Bearer $API_KEY"