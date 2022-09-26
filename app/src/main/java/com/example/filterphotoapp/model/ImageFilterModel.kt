package com.example.filterphotoapp.model

import android.graphics.Bitmap
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter

data  class ImageFilterModel (
    val name: String,
    val filter: GPUImageFilter,
    val filterPreview: Bitmap
)
