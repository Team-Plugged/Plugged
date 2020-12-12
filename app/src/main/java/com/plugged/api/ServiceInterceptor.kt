package com.plugged.api

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor{

    var token : String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmYzE3NWNiYzBiMDdlMmU0YzRlZjI0MCIsImlhdCI6MTYwNjgxNjM4NSwiZXhwIjoxNjA5NDA4Mzg1fQ.jLWW3AxjDFn4ZnPNT9MGJfFtViHI3Ozfxm-RTkJ9oLA";

    fun Token(token: String ) {
        this.token = token;
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if(request.header("No-Authentication")==null){
            //val token = getTokenFromSharedPreference();
            //or use Token Function
            if(!token.isNullOrEmpty())
            {
                val finalToken =  "Bearer "+token
                request = request.newBuilder()
                    .addHeader("Authorization",finalToken)
                    .build()
            }

        }

        return chain.proceed(request)
    }

}