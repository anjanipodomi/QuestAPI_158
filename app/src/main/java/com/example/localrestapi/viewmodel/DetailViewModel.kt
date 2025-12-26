package com.example.localrestapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.example.localrestapi.modeldata.DataSiswa
import com.example.localrestapi.repositori.RepositoryDataSiswa
import com.example.localrestapi.uicontroller.route.DestinasiDetail


sealed interface StatusUIDetail {
    data class Success(val siswa: DataSiswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int =
        savedStateHandle.get<Int>(DestinasiDetail.itemIdArg) ?: 0

    var statusUIDetail: StatusUIDetail by mutableStateOf(StatusUIDetail.Loading)
        private set

    init {
        getSatuSiswa()
    }

    fun getSatuSiswa() {
        viewModelScope.launch {
            statusUIDetail = StatusUIDetail.Loading
            statusUIDetail = try {
                StatusUIDetail.Success(
                    repositoryDataSiswa.getSatuSiswa(idSiswa)
                )
            } catch (e: IOException) {
                StatusUIDetail.Error
            } catch (e: HttpException) {
                StatusUIDetail.Error
            }
        }
    }

    fun hapusSatuSiswa() {
        viewModelScope.launch {
            try {
                val response = repositoryDataSiswa.hapusSatuSiswa(idSiswa)
                if (response.isSuccessful) {
                    println("Sukses hapus data")
                } else {
                    println("Gagal hapus data")
                }
            } catch (e: IOException) {
                println("Error IO: ${e.message}")
            } catch (e: HttpException) {
                println("Error HTTP: ${e.message}")
            }
        }
    }
}