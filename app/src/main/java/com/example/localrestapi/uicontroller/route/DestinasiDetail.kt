package com.example.localrestapi.uicontroller.route

object DestinasiDetail {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa

    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}