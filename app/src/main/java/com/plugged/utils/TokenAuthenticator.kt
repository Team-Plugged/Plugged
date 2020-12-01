package com.plugged.utils

import android.R.attr.password
import com.plugged.db.PatientDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*


class TokenAuthenticator(private val db: PatientDao) :
    Authenticator {

//    var repository = PluggedRepository(ApiHelper)
    override fun authenticate(route: Route?, response: Response): Request? {
        // This is a synchronous call
    val credential = Credentials.basic("hospital@example.com", "123456")

    if (credential == response.request.header("Authorization")) {
        return null // If we already failed with these credentials, don't retry.
    }
    var tok =""
    GlobalScope.launch(Dispatchers.Main)
    {
        tok = getUpdatedToken()

    }
        return response.request.newBuilder()
            .header("Authorization", tok)
            .build()
    }

    private fun getUpdatedToken(): String {
        var token = ""
        var accessToken = db.getToken()
        accessToken.observeForever {
            token = it.token
        }

        return token
    }
}