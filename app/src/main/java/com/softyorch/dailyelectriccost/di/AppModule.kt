package com.softyorch.dailyelectriccost.di

import android.content.Context
import com.softyorch.dailyelectriccost.data.local.Datastore
import com.softyorch.dailyelectriccost.data.repository.datastore.DsRepository
import com.softyorch.dailyelectriccost.data.repository.red21Repository.Red21Repository
import com.softyorch.dailyelectriccost.domain.dsUseCases.DeleteData
import com.softyorch.dailyelectriccost.domain.dsUseCases.DsUseCases
import com.softyorch.dailyelectriccost.domain.dsUseCases.GetData
import com.softyorch.dailyelectriccost.domain.dsUseCases.SaveData
import com.softyorch.dailyelectriccost.domain.redUseCases.GetDataBalance
import com.softyorch.dailyelectriccost.domain.redUseCases.GetDataGenerationTruncate
import com.softyorch.dailyelectriccost.domain.redUseCases.GetDataMarketsTruncate
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
            getDataBalance = GetDataBalance(repository),
            getDataGenerationTruncate = GetDataGenerationTruncate(repository),
            getDataMarketsTruncate = GetDataMarketsTruncate(repository)
        )

    @Singleton
    @Provides
    fun providesDatastore(context: Context): Datastore = Datastore(context)

    @Singleton
    @Provides
    fun providesDatastoreUseCases(dsRepository: DsRepository): DsUseCases = DsUseCases(
        saveData = SaveData(dsRepository),
        getData = GetData(dsRepository),
        deleteData = DeleteData(dsRepository)
    )
}