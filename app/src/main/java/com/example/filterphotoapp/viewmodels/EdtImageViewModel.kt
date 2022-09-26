package com.example.filterphotoapp.viewmodels

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filterphotoapp.model.ImagePreviewModel
import com.example.filterphotoapp.repository.EdtImageRepository
import com.example.filterphotoapp.utilities.Coroutines
import kotlinx.coroutines.delay

class EdtImageViewModel (private val edtImageRepository: EdtImageRepository): ViewModel() {

    private val imagePreviewDataState = MutableLiveData<ImagePreviewModel>()
    val uistate: LiveData<ImagePreviewModel> get() = imagePreviewDataState

    fun prepareImagePreview(imageUri: Uri){
        Coroutines.io{
            kotlin.runCatching {
                renderImagePreviewUiState(isLoading = true)
                edtImageRepository.prepareImagePreview(imageUri)
            }.onSuccess { bitmap ->
                if (bitmap != null){
                    renderImagePreviewUiState(bitmap = bitmap)
                }else{
                    renderImagePreviewUiState(error = "não foi possível preparar a imagem")
                }
            }.onFailure {
                renderImagePreviewUiState(error = it.message.toString())
            }

        }
    }

    private fun renderImagePreviewUiState(
        isLoading: Boolean = false,
        bitmap: Bitmap? = null,
        error: String? = null)
    {
        val dataState = ImagePreviewModel(isLoading,bitmap,error)
        imagePreviewDataState.postValue(dataState)
    }
}