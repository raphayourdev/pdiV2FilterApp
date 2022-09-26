package com.example.filterphotoapp.mapper

import com.example.filterphotoapp.model.ImageFilterModel
import jp.co.cyberagent.android.gpuimage.GPUImage

interface EditImageMapper {
    fun mapToImageFilters(gpuImage: GPUImage): List<ImageFilterModel>

}

