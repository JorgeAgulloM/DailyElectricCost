package com.softyorch.dailyelectriccost.di

import com.softyorch.dailyelectriccost.data.network.red21Api.RedClient
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
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://apidatos.ree.es/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideGetRed21Data(retrofit: Retrofit): RedClient = retrofit.create(RedClient::class.java)

}