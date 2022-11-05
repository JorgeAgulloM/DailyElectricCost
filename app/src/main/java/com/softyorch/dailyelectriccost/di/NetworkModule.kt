package com.softyorch.dailyelectriccost.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() = Retrofit.Builder()
        .baseUrl("https://apidatos.ree.es/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    
}