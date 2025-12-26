package com.example.localrestapi.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.localrestapi.repositori.AplikasiDataSiswa
import com.example.localrestapi.viewmodel.*

fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiDataSiswa

object PenyediaViewModel {
    val Factory = viewModelFactory {

        // HOME
        initializer {
            HomeViewModel(
                aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }

        // ENTRY
        initializer {
            EntryViewModel(
                aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }

        // DETAIL (PAKAI SavedStateHandle)
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }

        // EDIT (PAKAI SavedStateHandle)
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }
    }
}
