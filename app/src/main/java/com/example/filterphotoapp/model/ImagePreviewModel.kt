package com.example.filterphotoapp.model

import android.graphics.Bitmap

data class ImagePreviewModel(
    var isLoading: Boolean,
    val bitmap: Bitmap?,
    val error: String?
)
