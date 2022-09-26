package com.example.filterphotoapp.repository

import android.graphics.Bitmap
import android.net.Uri
import com.example.filterphotoapp.model.ImageFilterModel

interface EdtImageRepository {
    suspend fun prepareImagePreview(imageUri: Uri):Bitmap?
    suspend fun getImageFilters(imageUri: Bitmap): List<ImageFilterModel>
}