package com.app.telefric.makeen.domain.interactor

import com.app.telefric.makeen.data.model.response.MakeenResponse
import com.app.telefric.makeen.domain.entity.MakeenParams
import com.app.telefric.makeen.domain.repsitory.MakeenRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMakeenUseCase @Inject constructor(private val makeenRepository: MakeenRepository) {

    fun fetchMakeen(params: MakeenParams): Single<MakeenResponse> {
        return makeenRepository.fetchMakeen(params)
    }

}