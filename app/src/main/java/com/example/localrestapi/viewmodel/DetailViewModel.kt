package com.example.localrestapi.viewmodel

import

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
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

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