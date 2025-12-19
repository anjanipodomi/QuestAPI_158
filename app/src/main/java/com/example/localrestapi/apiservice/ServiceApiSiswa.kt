package com.example.localrestapi.apiservice

interface ServiceApiSiswa {
    @GET(value= "bacaTeman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @POST(value= "insertTm".php)
    suspend fun postSiswa(@Body dataSiswa: DataSiswa):retrofit2.Response<Void>
}