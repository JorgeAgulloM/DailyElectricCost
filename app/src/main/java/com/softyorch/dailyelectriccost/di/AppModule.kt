package com.softyorch.dailyelectriccost.di

import com.softyorch.dailyelectriccost.data.repository.red21Repository.Red21Repository
import com.softyorch.dailyelectriccost.domain.redUseCases.GetDataDefault
import com.softyorch.dailyelectriccost.domain.redUseCases.GetDataGeoTruncate
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesRedUsesCases(repository: Red21Repository): RedUsesCases =
        RedUsesCases(
            getDataDefault = GetDataDefault(repository),
            getDataGeoTruncate = GetDataGeoTruncate(repository)
        )
}