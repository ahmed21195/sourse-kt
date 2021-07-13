package com.ahmed.makeeen_task.makeen.domain.interactor

import com.ahmed.makeeen_task.makeen.data.model.response.MakeenResponse
import com.ahmed.makeeen_task.makeen.domain.entity.MakeenParams
import com.ahmed.makeeen_task.makeen.domain.repsitory.MakeenRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMakeenUseCase @Inject constructor(private val makeenRepository: MakeenRepository) {

    fun fetchMakeen(params: MakeenParams): Single<MakeenResponse> {
        return makeenRepository.fetchMakeen(params)
    }

}