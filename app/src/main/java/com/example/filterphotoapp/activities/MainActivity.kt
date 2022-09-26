package com.example.filterphotoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.filterphotoapp.databinding.ActivityMainBinding
import com.example.filterphotoapp.di.FilterImageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val modules: Array<Module> = emptyArray()


    companion object {
        private const val REQUEST_CODE_PICK_IMAGE = 1
        const val KEY_IMAGE_URI = "imageUri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        loadKoinModules(modules.toList())

    }
    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(modules.toList())
    }

    private fun setListener(){
        binding.filterPhotoImportBtn.setOnClickListener(){
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
                pickerIntent ->
                pickerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivityForResult(pickerIntent, REQUEST_CODE_PICK_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK){
            data?.data.let { imageUri ->
                Intent(applicationContext, EdtImageActivity::class.java).also {
                    edtImageItent ->
                    edtImageItent.putExtra(KEY_IMAGE_URI,imageUri)
                    startActivity(edtImageItent)
                }

            }
        }
    }
}