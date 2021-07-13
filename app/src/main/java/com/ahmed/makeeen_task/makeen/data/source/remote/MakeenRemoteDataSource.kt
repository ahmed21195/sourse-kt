package com.ahmed.makeeen_task.makeen.data.source.remote

import com.ahmed.makeeen_task.makeen.data.model.response.MakeenResponse
import com.ahmed.makeeen_task.makeen.domain.entity.MakeenParams
import io.reactivex.Single
import javax.inject.Inject

class MakeenRemoteDataSource @Inject constructor(
    private val makeenApiService: MakeenApiService
) {

     fun fetchMakeen(params: MakeenParams): Single<MakeenResponse> {
         return makeenApiService.getMakeen(
             params.page,
             params.per_page,
         )
     }

}