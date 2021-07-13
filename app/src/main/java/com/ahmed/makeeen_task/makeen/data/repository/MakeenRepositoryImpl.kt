package com.ahmed.makeeen_task.makeen.data.repository


import com.ahmed.makeeen_task.makeen.data.model.response.MakeenResponse
import com.ahmed.makeeen_task.makeen.data.source.remote.MakeenRemoteDataSource
import com.ahmed.makeeen_task.makeen.domain.entity.MakeenParams
import com.ahmed.makeeen_task.makeen.domain.repsitory.MakeenRepository
import io.reactivex.Single
import javax.inject.Inject


class MakeenRepositoryImpl @Inject constructor(
    private val makeenRemoteDataSource: MakeenRemoteDataSource
) : MakeenRepository {

    override fun fetchMakeen(makeenParams: MakeenParams): Single<MakeenResponse> {
        return makeenRemoteDataSource.fetchMakeen(makeenParams)
    }



}
