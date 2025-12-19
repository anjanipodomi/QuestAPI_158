package com.example.localrestapi.repositori

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface ContainerApp{
    val repositoryDataSiswa: RepositoryDataSiswa
}

class DefaultContainerApp : ContainerApp{
    private val baseurl = "http://10.0.2.2/umyTI/"

    val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }
    val klien = OkHttpClient.Builder()
        .addInterceptor(interceptor = logging)
        .build()

    private val retrofit: Retrofit.Builder()
    .baseUrl(baseUrl= baseurl)
    .addConverterFactory(
    factory= Json{
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }.asConverterFactory(contentType="application/json".toMediaType())
    )
    .client(client= klien)
    .build()

    private val retrofitService : ServiceApiSiswa by lazy{
        retrofit.create(ServiceApiSiswa::class.java)
    }

    override val repositoryDataSiswa: RepositoryDataSiswa by lazy {
        JaringanRepositoryDataSiswa(serviceApiSiswa= retrofitService) }
}

