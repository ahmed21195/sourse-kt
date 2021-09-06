package com.app.telefric.makeen.presentation.di

import com.app.telefric.makeen.data.repository.MakeenRepositoryImpl
import com.app.telefric.makeen.data.source.remote.MakeenApiService
import com.app.telefric.makeen.domain.repsitory.MakeenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class MakeenModule {

    @Provides
    fun providesMakeenRepository(makeenRepositoryImpl: MakeenRepositoryImpl): MakeenRepository = makeenRepositoryImpl

    @Provides
    fun providesMakeenApiService(builder: Retrofit.Builder): MakeenApiService {
        return builder.build().create(MakeenApiService::class.java)
    }


}