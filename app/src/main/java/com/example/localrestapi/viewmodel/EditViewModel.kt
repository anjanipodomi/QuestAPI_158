package com.example.localrestapi.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localrestapi.modeldata.DetailSiswa
import com.example.localrestapi.modeldata.UIStateSiswa
import com.example.localrestapi.modeldata.toDataSiswa
import com.example.localrestapi.modeldata.toUiStateSiswa
import com.example.localrestapi.repositori.RepositoryDataSiswa
import com.example.localrestapi.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException
import com.example.localrestapi.uicontroller.route.DestinasiEdit


class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        savedStateHandle.get<Int>(DestinasiEdit.itemIdArg) ?: 0

    init {
        if (idSiswa != 0) {
            viewModelScope.launch {
                uiStateSiswa = repositoryDataSiswa
                    .getSatuSiswa(idSiswa)
                    .toUiStateSiswa(true)
            }
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    private fun validasiInput(
        uiState: DetailSiswa = uiStateSiswa.detailSiswa
    ): Boolean {
        return with(uiState) {
            nama.isNotBlank() &&
                    alamat.isNotBlank() &&
                    telpon.isNotBlank()
        }
    }

    fun editSatuSiswa() {
        viewModelScope.launch {
            if (validasiInput()) {
                try {
                    val response = repositoryDataSiswa.editSatuSiswa(
                        idSiswa,
                        uiStateSiswa.detailSiswa.toDataSiswa()
                    )
                    if (response.isSuccessful) {
                        println("Update sukses")
                    } else {
                        println("Update gagal")
                    }
                } catch (e: IOException) {
                    println("IO Error: ${e.message}")
                } catch (e: HttpException) {
                    println("HTTP Error: ${e.message}")
                }
            }
        }
    }
}