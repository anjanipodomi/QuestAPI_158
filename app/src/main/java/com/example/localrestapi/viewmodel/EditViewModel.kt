package com.example.localrestapi.viewmodel

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoryDataSiswa
                .getSatuSiswa(idSiswa)
                .toUiStateSiswa(true)
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