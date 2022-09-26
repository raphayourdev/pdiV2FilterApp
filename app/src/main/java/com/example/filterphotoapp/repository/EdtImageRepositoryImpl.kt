package com.example.filterphotoapp.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.filterphotoapp.mapper.EditImageMapper
import com.example.filterphotoapp.model.ImageFilterModel
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.*
import java.io.InputStream

class EdtImageRepositoryImpl (private val context: Context, private val mapper: EditImageMapper):EdtImageRepository
{
    override suspend fun prepareImagePreview(imageUri: Uri): Bitmap? {
        getInputStreamFromUri(imageUri)?.let{ inputStream ->
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            val width = context.resources.displayMetrics.widthPixels
            val height = ((originalBitmap.height * width) / originalBitmap.width)
            return  Bitmap.createScaledBitmap(originalBitmap,width,height,false)
        }?: return null
    }

    override suspend fun getImageFilters(imageUri: Bitmap): List<ImageFilterModel> {
        val gpuImage = GPUImage(context).apply {
            setImage(imageUri)
        }
        return mapper.mapToImageFilters(gpuImage)
    }

    private fun getInputStreamFromUri(uri: Uri): InputStream? {
        return context.contentResolver.openInputStream(uri)
    }
}