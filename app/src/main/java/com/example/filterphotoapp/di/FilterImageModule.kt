package com.example.filterphotoapp.di

import android.content.Context
import com.example.filterphotoapp.mapper.EditImageMapper
import com.example.filterphotoapp.repository.EdtImageRepository
import com.example.filterphotoapp.repository.EdtImageRepositoryImpl
import com.example.filterphotoapp.viewmodels.EdtImageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object FilterImageModule{

    fun appModule(): List<Module>{
        return listOf(
            viewModelModule,
            repository
        )
    }

val viewModelModule = module {
    viewModel{
        EdtImageViewModel(get())
    }
}

val repository = module {
    fun provideRepository(context: Context, mapper: EditImageMapper): EdtImageRepository {
        return EdtImageRepositoryImpl(context,mapper)
    }
    single{provideRepository(androidContext(),get())}
 }

}