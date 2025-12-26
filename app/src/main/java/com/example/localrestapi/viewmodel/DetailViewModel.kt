package com.example.localrestapi.viewmodel

import

sealed interface StatusUIDetail {
    data class Success(val siswa: DataSiswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}

