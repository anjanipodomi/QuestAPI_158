package com.example.localrestapi.apiservice

import com.example.localrestapi.modeldata.DataSiswa
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface ServiceApiSiswa {
    @GET(value= "bacaTeman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @POST(value= "insertTm.php")
    suspend fun postSiswa(@Body dataSiswa: DataSiswa):retrofit2.Response<Void>

    @GET("baca1teman.php")
    suspend fun getSiswaById(
        @Query("id") id: Int
    ): DataSiswa

    @PUT("editTM.php")
    suspend fun updateSiswa(
        @Query("id") id: Int,
        @Body siswa: DataSiswa
    ): Response<Void>

    @DELETE("deleteTM.php")
    suspend fun deleteSiswa(
        @Query("id") id: Int
    ): Response<Void>


}