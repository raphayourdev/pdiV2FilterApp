package com.example.filterphotoapp.activities

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.filterphotoapp.databinding.ActivityEdtImageBinding
import com.example.filterphotoapp.di.FilterImageModule
import com.example.filterphotoapp.viewmodels.EdtImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class EdtImageActivity : AppCompatActivity(FilterImageModule.appModule().toTypedArray().size) {

    lateinit var binding: ActivityEdtImageBinding
    private val viewModel: EdtImageViewModel by viewModel()
    private val modules: Array<Module> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(modules.toList())
        super.onCreate(savedInstanceState)
        binding = ActivityEdtImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackButton()
        displayImagePreview()
        setupObserves()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(modules.toList())
    }

    private fun setupObserves(){
        viewModel.uistate.observe(this) {
            val dataState = it ?: return@observe
                if (dataState.isLoading) {
                    binding.previewProgresBar.visibility = View.VISIBLE
                } else  {
                    binding.previewProgresBar.visibility = View.GONE
                }
                    dataState.bitmap?.let { bitmap ->
                        binding.previewProgresBar.visibility = View.GONE
                        binding.imgPreview.setImageBitmap(bitmap)
                        binding.imgPreview.visibility = View.VISIBLE
                        } ?: kotlin.run {
                        dataState.error?.let { error ->
                            Toast.makeText(this, error, Toast.LENGTH_LONG)
                        }
                    }
             }
        }

    private fun displayImagePreview(){
        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let {
            imageUri->
            val inputStream = contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.imgPreview.setImageBitmap(bitmap)
            binding.imgPreview.visibility = View.VISIBLE

        }
    }

    private fun setBackButton(){
        binding.backBtn.setOnClickListener(){
            onBackPressed()
        }
    }

}