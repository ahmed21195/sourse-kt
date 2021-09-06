package com.app.telefric.makeen.data.repository


import com.app.telefric.makeen.data.model.response.MakeenResponse
import com.app.telefric.makeen.data.source.remote.MakeenRemoteDataSource
import com.app.telefric.makeen.domain.entity.MakeenParams
import com.app.telefric.makeen.domain.repsitory.MakeenRepository
import io.reactivex.Single
import javax.inject.Inject


class MakeenRepositoryImpl @Inject constructor(
    private val makeenRemoteDataSource: MakeenRemoteDataSource
) : MakeenRepository {

    override fun fetchMakeen(makeenParams: MakeenParams): Single<MakeenResponse> {
        return makeenRemoteDataSource.fetchMakeen(makeenParams)
    }



}
