package com.app.telefric.makeen.domain.repsitory


import com.app.telefric.makeen.data.model.response.MakeenResponse
import com.app.telefric.makeen.domain.entity.MakeenParams
import io.reactivex.Single

interface MakeenRepository {
    fun fetchMakeen(params: MakeenParams): Single<MakeenResponse>
}